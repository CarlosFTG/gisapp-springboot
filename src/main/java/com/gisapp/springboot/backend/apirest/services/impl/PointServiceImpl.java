package com.gisapp.springboot.backend.apirest.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.converter.UserConverter;
import com.gisapp.springboot.backend.apirest.dao.ILinesGenericDAO;
import com.gisapp.springboot.backend.apirest.dao.IPointsDAO;
import com.gisapp.springboot.backend.apirest.dao.IPointsGenericDao;
import com.gisapp.springboot.backend.apirest.dao.IPolygonDAO;
import com.gisapp.springboot.backend.apirest.dao.IPolygonsGenericDAO;
import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.gisapp.springboot.backend.apirest.services.IPointService;
import com.vividsolutions.jts.io.ParseException;

@Service
public class PointServiceImpl implements IPointService {
	
	final static Logger logger = Logger.getLogger(PointServiceImpl.class);

	@Autowired
	private IPointsGenericDao pointsGenericDao;
	
	@Autowired
	private IPointsDAO pointsDao;
	
	@Autowired
	private IPolygonsGenericDAO polygonsGenericDao;
	
	@Autowired
	private IPolygonDAO polygonDAO;
	
	
	
	@Autowired
	private IPointsDAO geometriesDao;

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
	public void removePoint(List<PointsEntity> pointsList) {
		
		
		//as having problems to get the attributes on the front end, the
		//points to remove are found by user id and coords
		//the with the point id, delete method is called
		
		for(PointsEntity point:pointsList) {
			
			pointsGenericDao.delete(point);
		}
		
	}
	
	

	

	@SuppressWarnings("unchecked")
	@Override
	public UserBean findFeaturesByUserId(String userId) throws ParseException, JSONException {
		
		UserEntity userFound= (UserEntity) geometriesDao.findPointsByUserId(userId);
		UserBean userToConvert = UserConverter.convertToUserBean(userFound);
		return userToConvert;
		
	}

	@Override
	public List<PointsEntity> findPointsIntoAPolygon(String polygon_id) throws ParseException, IOException, JSONException {


		
		Long polygonId=Long.parseLong(polygon_id);
		
		PolygonEntity polygonEntity =polygonDAO.findPolygonById(polygonId);
		 
		List<PointsEntity> pointsIntoPolygonList = pointsDao.findPointsIntoAPolygon(polygonEntity);
		
		
		return pointsIntoPolygonList;
	}
	
	

}
