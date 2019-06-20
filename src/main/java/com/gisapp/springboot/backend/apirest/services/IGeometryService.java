package com.gisapp.springboot.backend.apirest.services;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.gisapp.springboot.backend.apirest.models.bean.PointBean;
import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.vividsolutions.jts.io.ParseException;

public interface IGeometryService {

	public List<PointsEntity> findAll();

	public void savePoint(PointsEntity geometry);

	public void delete(PointsEntity cliente);
	
	public UserBean findPointByUserId(String userId) throws ParseException, JSONException;
	
	public UserBean findPointsIntoAPolygon(String polygon) throws ParseException, IOException;

	void savePolygons(PolygonEntity polygon);

	void saveLine(LineEntity line);

}
