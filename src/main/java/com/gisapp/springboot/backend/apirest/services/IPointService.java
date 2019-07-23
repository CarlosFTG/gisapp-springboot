package com.gisapp.springboot.backend.apirest.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.gisapp.springboot.backend.apirest.models.bean.PointBean;
import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.vividsolutions.jts.io.ParseException;

public interface IPointService {

	public List<PointsEntity> findAll();

	public void savePoint(PointsEntity geometry);

	public void delete(PointsEntity cliente);
	
	public UserBean findFeaturesByUserId(String userId) throws ParseException, JSONException;
	
	public List<Map<String, String>> findPointsIntoAPolygon(String polygon) throws ParseException, IOException, JSONException;

	void savePolygons(PolygonEntity polygon);

	void saveLine(LineEntity line);

	void removePoint(List<PointsEntity> pointsList);

}