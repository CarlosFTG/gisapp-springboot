package com.gisapp.springboot.backend.apirest.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.converter.PointsConverter;
import com.gisapp.springboot.backend.apirest.converter.PolygonsConverter;
import com.gisapp.springboot.backend.apirest.converter.UserConverter;
import com.gisapp.springboot.backend.apirest.dao.IGeometriesDAO;
import com.gisapp.springboot.backend.apirest.dao.ILinesGenericDAO;
import com.gisapp.springboot.backend.apirest.dao.IPointsGenericDao;
import com.gisapp.springboot.backend.apirest.dao.IPolygonsGenericDAO;
import com.gisapp.springboot.backend.apirest.dao.ITempPolygonGenericDAO;
import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.gisapp.springboot.backend.apirest.models.entity.TempPolygonEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.gisapp.springboot.backend.apirest.services.IGeometryService;
import com.vividsolutions.jts.io.ParseException;

@Service
public class GeometryServiceImpl implements IGeometryService {

	@Autowired
	private IPointsGenericDao pointsGenericDao;
	
	@Autowired
	private IPolygonsGenericDAO polygonsGenericDao;
	
	@Autowired
	private ITempPolygonGenericDAO tempPolygonsGenericDao;
	
	@Autowired
	private ILinesGenericDAO linesGenericDao;
	
	@Autowired
	private IGeometriesDAO geometriesDao;

	@Override
	@Transactional(readOnly = true)
	public List<PointsEntity> findAll() {
		return (List<PointsEntity>) pointsGenericDao.findAll();
	}

	@Override
	@Transactional
	public void savePoint(PointsEntity point) {

		pointsGenericDao.save(point);
	}
	
	@Override
	@Transactional
	public void savePolygons(PolygonEntity polygon) {

		polygonsGenericDao.save(polygon);
	}
	
	@Override
	@Transactional
	public void saveLine(LineEntity line) {

		linesGenericDao.save(line);
	}

	@Override
	@Transactional
	public void delete(PointsEntity cliente) {
		pointsGenericDao.delete(cliente);

	}

	@SuppressWarnings("unchecked")
	@Override
	public UserBean findPointByUserId(String userId) throws ParseException, JSONException {
		
		UserEntity userFound= (UserEntity) geometriesDao.findPointsByUserId(userId);
		UserBean userToConvert = UserConverter.convertToUserBean(userFound);
		return userToConvert;
		
	}

	@Override
	public List<Map<String, String>> findPointsIntoAPolygon(String polygon) throws ParseException, IOException, JSONException {

		@SuppressWarnings("unused")
		TempPolygonEntity polygonEntity = PolygonsConverter.convertFromStringToPolygonEntity(polygon);
		
		tempPolygonsGenericDao.save(polygonEntity);
				
		List<Map<String, String>> pointsIntoPolygon=PointsConverter.convertToGeometryBeanList( geometriesDao.findPointsIntoAPolygon(polygonEntity));
		
		tempPolygonsGenericDao.delete(polygonEntity);
		
		
		return pointsIntoPolygon;
	}
	
	

}
