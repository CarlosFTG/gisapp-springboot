package com.gisapp.springboot.backend.apirest.converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gisapp.springboot.backend.apirest.models.bean.GeometryBean;
import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class GeometryEntityConverter {

	public List<GeometryEntity> convertToGeometryEntityList(List<NonGeometryEntity> nonGeometryList)
			throws ParseException {

		List<GeometryEntity> geometryEntityList = new ArrayList<>();

		GeometryEntity geometryEntity = new GeometryEntity();

		for (NonGeometryEntity nonGeometry : nonGeometryList) {

			geometryEntity = convertToGeometryEntity(nonGeometry);
			geometryEntityList.add(geometryEntity);
		}

		return geometryEntityList;
	}

	public static List<Map<String, String>> convertToGeometryBean(List<GeometryEntity> geometryEntity)
			throws ParseException, JSONException {

		List<Map<String, String>> nonGeometryEntityList = new ArrayList<>();

		GeometryBean geometryBean = new GeometryBean();

		for (GeometryEntity geometry : geometryEntity) {

			Map<String, String> coordinatesMap = new LinkedHashMap<>();

			geometryBean = convertToGeometryBean(geometry);

			coordinatesMap.put("point", geometryBean.getGeom().toString());

			nonGeometryEntityList.add(coordinatesMap);
		}

		return nonGeometryEntityList;

	}

	public static GeometryEntity convertToGeometryEntity(NonGeometryEntity nonGeometry) throws ParseException {

		GeometryEntity geometryEntity = new GeometryEntity();

		geometryEntity.setUserId(Long.parseLong(nonGeometry.getUserId()));

		geometryEntity.setPointName(nonGeometry.getPointName());
		geometryEntity.setGeom(wktToGeometry(nonGeometry.getGeom()));

		geometryEntity.setUserEmail(nonGeometry.getUserEmail());

		geometryEntity.getGeom().setSRID(3857);

		return geometryEntity;

	}

	public static GeometryBean convertToGeometryBean(GeometryEntity geometryEntity)
			throws ParseException, JSONException {

		String featureType = new String();

		String lat = new String();

		List<String> geoPropertiesList = new ArrayList<>();

		if (geometryEntity.getGeom().toString().contains("POINT")) {

			geoPropertiesList = extractFeatureType(geometryEntity.getGeom().toString());

			featureType = geoPropertiesList.get(0);
			lat = geoPropertiesList.get(1);
		}

		GeometryBean geometryBean = new GeometryBean();

		String mapLat = lat;

		JSONObject geoJson = new JSONObject();

		JSONObject geoJsonGeom = new JSONObject();

		JSONObject geoJsonProperties = new JSONObject();

		geoJsonGeom.put("type", featureType);

		geoJsonGeom.put("coordinates", mapLat);

		geoJson.put("type", "feature");

		geoJson.put("geometry", geoJsonGeom);

		geoJsonProperties.put("name", geometryEntity.getPointName());

		geoJson.put("properties", geoJsonProperties);
		geometryBean.setUserId(geometryEntity.getId());
		geometryBean.setPointName(geometryEntity.getPointName());
		geometryBean.setGeom(geoJson);

		return geometryBean;

	}

	/**
	 * Method to extract the type of feature, lat and long
	 * 
	 * @param geoInfo
	 * @return
	 */
	private static List<String> extractFeatureType(String geoInfo) {

		List<String> stringExtraction = new ArrayList<>();

		String typeOfFeature = geoInfo.substring(0, 5);

		String coords = geoInfo.substring(7, 23);

		if (typeOfFeature != null) {
			stringExtraction.add(typeOfFeature);
		}

		stringExtraction.add(coords);

		return stringExtraction;
	}

	public static Point wktToGeometry(String wellKnownText) throws ParseException {

		return (Point) new WKTReader().read(wellKnownText);
	}

}
