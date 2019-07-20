package com.gisapp.springboot.backend.apirest.converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gisapp.springboot.backend.apirest.models.bean.PointBean;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class PointsConverter {

	public static List<PointsEntity> convertToPointEntityList(List<NonGeometryEntity> nonGeometryList)
			throws ParseException {

		List<PointsEntity> geometryEntityList = new ArrayList<>();

		PointsEntity geometryEntity = new PointsEntity();

		for (NonGeometryEntity nonGeometry : nonGeometryList) {

			geometryEntity = convertToPointEntity(nonGeometry);
			geometryEntityList.add(geometryEntity);
		}

		return geometryEntityList;
	}

	public static List<Map<String, String>> convertToGeometryBeanList(List<PointsEntity> geometryEntityList)
			throws ParseException, JSONException {

		List<Map<String, String>> nonGeometryEntityList = new ArrayList<>();

		PointBean geometryBean = new PointBean();

		for (PointsEntity geometry : geometryEntityList) {

			Map<String, String> coordinatesMap = new LinkedHashMap<>();

			geometryBean = convertToPointBean(geometry);

			coordinatesMap.put("point", geometryBean.getGeom().toString());

			nonGeometryEntityList.add(coordinatesMap);
		}

		return nonGeometryEntityList;

	}

	public static PointsEntity convertToPointEntity(NonGeometryEntity nonGeometry) throws ParseException {

		PointsEntity geometryEntity = new PointsEntity();
		
		geometryEntity.setId(nonGeometry.getId());
		if(nonGeometry.getUserId() != null) {
			geometryEntity.setUserId(Long.parseLong(nonGeometry.getUserId()));

		}
		geometryEntity.setPointName(nonGeometry.getPointName());
		geometryEntity.setGeom(wktToGeometry(nonGeometry.getGeom()));
		geometryEntity.setFacility(nonGeometry.getFacility());
		geometryEntity.setUserEmail(nonGeometry.getUserEmail());

		geometryEntity.getGeom().setSRID(3857);

		return geometryEntity;

	}

	public static PointBean convertToPointBean(PointsEntity geometryEntity)
			throws ParseException, JSONException {

		String featureType = new String();

		String lat = new String();

		List<String> geoPropertiesList = new ArrayList<>();

		if (geometryEntity.getGeom().toString().contains("POINT")) {

			geoPropertiesList = extractFeatureType(geometryEntity.getGeom().toString());

			featureType = geoPropertiesList.get(0);
			lat = geoPropertiesList.get(1);
		}

		PointBean geometryBean = new PointBean();

		String mapLat = lat;

		JSONObject geoJson = new JSONObject();

		JSONObject geoJsonGeom = new JSONObject();

		JSONObject geoJsonProperties = new JSONObject();

		geoJsonGeom.put("type", featureType);

		geoJsonGeom.put("coordinates", mapLat);

		geoJson.put("type", "feature");

		geoJson.put("geometry", geoJsonGeom);

		geoJsonProperties.put("name", geometryEntity.getPointName());
		
		geoJsonProperties.put("id", geometryEntity.getId());
		
		geoJsonProperties.put("facility", geometryEntity.getFacility());

		geoJson.put("properties", geoJsonProperties);
		geometryBean.setUserId(geometryEntity.getId());
		geometryBean.setPointName(geometryEntity.getPointName());
		geometryBean.setGeom(geoJson);

		return geometryBean;

	}
	
	public static List<JSONObject> convertoListGeometriesEntitiesListToGeometriesBeansList(List<PointsEntity> geometryEntityList) throws ParseException, JSONException{
		
		List<JSONObject> geometryBeanList = new ArrayList<>();
				
		
		for(PointsEntity geometryEntity:geometryEntityList) {
			
			JSONObject geoJson = new JSONObject();
			
			geoJson.put("type", "MultiPoint");
			geoJson.put("coordinates", geometryEntity.getGeom());
			
			geometryBeanList.add(geoJson);
		}
		
		
		return geometryBeanList;
		
	}

	/**
	 * Method to extract the type of feature, lat and long
	 * 
	 * @param geoInfo
	 * @return
	 */
	private static List<String> extractFeatureType(String geoInfo) {

		List<String> stringExtraction = new ArrayList<>();

		String typeOfFeature = geoInfo.substring(0, 5).toLowerCase();
		
		String typeOfFeatureTreated=typeOfFeature.substring(0, 1).toUpperCase() + typeOfFeature.substring(1);

		String coords = geoInfo.substring(geoInfo.indexOf("(")+1,geoInfo.indexOf(")"));

		if (typeOfFeatureTreated != null) {
			stringExtraction.add(typeOfFeatureTreated);
		}

		stringExtraction.add(coords);

		return stringExtraction;
	}

	public static Point wktToGeometry(String wellKnownText) throws ParseException {

		return (Point) new WKTReader().read(wellKnownText);
	}

}
