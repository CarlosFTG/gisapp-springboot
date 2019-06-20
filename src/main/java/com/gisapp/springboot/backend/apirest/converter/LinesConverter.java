package com.gisapp.springboot.backend.apirest.converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gisapp.springboot.backend.apirest.models.bean.LineBean;
import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class LinesConverter {
	
	public static List<Map<String, String>> convertToLineBeanList(List<LineEntity> geometryEntityList)
			throws ParseException, JSONException {

		List<Map<String, String>> nonGeometryEntityList = new ArrayList<>();

		LineBean lineBean = new LineBean();

		for (LineEntity geometry : geometryEntityList) {

			Map<String, String> coordinatesMap = new LinkedHashMap<>();

			lineBean = convertToLineBean(geometry);

			coordinatesMap.put("line", lineBean.getGeom().toString());

			nonGeometryEntityList.add(coordinatesMap);
		}

		return nonGeometryEntityList;

	}
	
	
	public static LineEntity convertToGeometryEntity(NonGeometryEntity nonGeometry) throws ParseException {

		LineEntity lineEntity = new LineEntity();

		lineEntity.setUserId(Long.parseLong(nonGeometry.getUserId()));

		lineEntity.setLineName(nonGeometry.getPointName());
		lineEntity.setGeom(wktToGeometry(nonGeometry.getGeom().toString()));

		lineEntity.setUserEmail(nonGeometry.getUserEmail());

		lineEntity.getGeom().setSRID(3857);

		return lineEntity;

	}
	
	public static LineBean convertToLineBean(LineEntity geometryEntity)
			throws ParseException, JSONException {

		String featureType = new String();

		String lat = new String();

		List<String> geoPropertiesList = new ArrayList<>();

		if (geometryEntity.getGeom().toString().contains("LINESTRING")) {

			geoPropertiesList = extractFeatureType(geometryEntity.getGeom().toString());

			featureType = geoPropertiesList.get(0);
			lat = geoPropertiesList.get(1);
		}
		LineBean polygonBean = new LineBean();

		String mapLat = lat;

		JSONObject geoJson = new JSONObject();

		JSONObject geoJsonGeom = new JSONObject();

		JSONObject geoJsonProperties = new JSONObject();

		geoJsonGeom.put("type", featureType);

		geoJsonGeom.put("coordinates", mapLat);

		geoJson.put("type", "feature");

		geoJson.put("geometry", geoJsonGeom);

		geoJsonProperties.put("name", geometryEntity.getLineName());

		geoJson.put("properties", geoJsonProperties);
		polygonBean.setUserId(geometryEntity.getId());
		polygonBean.setLineName(geometryEntity.getLineName());
		polygonBean.setGeom(geoJson);

		return polygonBean;

	}
	
	/**
	 * Method to extract the type of feature, lat and long
	 * 
	 * @param geoInfo
	 * @return
	 */
	private static List<String> extractFeatureType(String geoInfo) {

		List<String> stringExtraction = new ArrayList<>();

		String typeOfFeature = geoInfo.substring(0, 10).toLowerCase();
		
		String typeOfFeatureTreated=typeOfFeature.substring(0, 1).toUpperCase() + typeOfFeature.substring(1);

		//String coords = geoInfo.substring(10, 31);
		
		String coords = geoInfo.substring(geoInfo.indexOf("(")+1,geoInfo.indexOf(")"));

		if (typeOfFeatureTreated != null) {
			stringExtraction.add(typeOfFeatureTreated);
		}

		stringExtraction.add(coords);

		return stringExtraction;
	}
	
	public static LineString wktToGeometry(String wellKnownText) throws ParseException {

		return (LineString) new WKTReader().read(wellKnownText);
	}
}
