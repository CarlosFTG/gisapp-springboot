package com.gisapp.springboot.backend.apirest.converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gisapp.springboot.backend.apirest.models.bean.PolygonBean;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class PolygonsConverter {
	
	public static List<PolygonEntity> convertToPolygonsEntityList(List<NonGeometryEntity> nonGeometryList)
			throws ParseException {

		List<PolygonEntity> geometryEntityList = new ArrayList<>();

		PolygonEntity geometryEntity = new PolygonEntity();

		for (NonGeometryEntity nonGeometry : nonGeometryList) {

			geometryEntity = convertToPolygonEntity(nonGeometry);
			geometryEntityList.add(geometryEntity);
		}

		return geometryEntityList;
	}
	
	public static PolygonEntity convertToPolygonEntity(NonGeometryEntity nonGeometry) throws ParseException {

		PolygonEntity geometryEntity = new PolygonEntity();

		geometryEntity.setUserId(Long.parseLong(nonGeometry.getUserId()));

		geometryEntity.setPointName(nonGeometry.getPointName());
		geometryEntity.setGeom(wktToGeometry(nonGeometry.getGeom()));

		geometryEntity.setUserEmail(nonGeometry.getUserEmail());

		geometryEntity.getGeom().setSRID(3857);

		return geometryEntity;

	}
	
	public static List<Map<String, String>> convertToPolygonBeanList(List<PolygonEntity> geometryEntityList)
			throws ParseException, JSONException {

		List<Map<String, String>> nonGeometryEntityList = new ArrayList<>();

		PolygonBean polygonBean = new PolygonBean();

		for (PolygonEntity geometry : geometryEntityList) {

			Map<String, String> coordinatesMap = new LinkedHashMap<>();

			polygonBean = convertToPolygonBean(geometry);

			coordinatesMap.put("polygon", polygonBean.getGeom().toString());

			nonGeometryEntityList.add(coordinatesMap);
		}

		return nonGeometryEntityList;

	}
	
	public static PolygonBean convertToPolygonBean(PolygonEntity geometryEntity)
			throws ParseException, JSONException {

		String featureType = new String();

		String lat = new String();

		List<String> geoPropertiesList = new ArrayList<>();

		if (geometryEntity.getGeom().toString().contains("POLYGON")) {

			geoPropertiesList = extractFeatureType(geometryEntity.getGeom().toString());

			featureType = geoPropertiesList.get(0);
			lat = geoPropertiesList.get(1);
		}
		PolygonBean polygonBean = new PolygonBean();

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
		polygonBean.setUserId(geometryEntity.getId());
		polygonBean.setPolygonName(geometryEntity.getPointName());
		polygonBean.setGeom(geoJson);

		return polygonBean;

	}
	public static PolygonEntity convertFromJSONToPolygonEntity(String polygon) throws ParseException {
		
		PolygonEntity polygonEntity = new PolygonEntity();
		
		polygonEntity.setGeom(wktToGeometry(polygon));
		
		return polygonEntity;	
	}
	
	public static PolygonEntity convertToGeometryEntity(NonGeometryEntity nonGeometry) throws ParseException {

		PolygonEntity polygonEntity = new PolygonEntity();

		polygonEntity.setUserId(Long.parseLong(nonGeometry.getUserId()));

		polygonEntity.setPointName(nonGeometry.getPointName());
		polygonEntity.setGeom(wktToGeometry(nonGeometry.getGeom().toString()));

		polygonEntity.setUserEmail(nonGeometry.getUserEmail());

		polygonEntity.getGeom().setSRID(3857);

		return polygonEntity;

	}
	

	/**
	 * Method to extract the type of feature, lat and long
	 * 
	 * @param geoInfo
	 * @return
	 */
	private static List<String> extractFeatureType(String geoInfo) {

		List<String> stringExtraction = new ArrayList<>();

		String typeOfFeature = geoInfo.substring(0, 7).toLowerCase();
		
		String typeOfFeatureTreated=typeOfFeature.substring(0, 1).toUpperCase() + typeOfFeature.substring(1);

		String coords = geoInfo.substring(geoInfo.indexOf("(")+1,geoInfo.indexOf(")"));

		if (typeOfFeatureTreated != null) {
			stringExtraction.add(typeOfFeatureTreated);
		}

		stringExtraction.add(coords);

		return stringExtraction;
	}
	
	public static Polygon wktToGeometry(String wellKnownText) throws ParseException {

		return (Polygon) new WKTReader().read(wellKnownText);
	}
}
