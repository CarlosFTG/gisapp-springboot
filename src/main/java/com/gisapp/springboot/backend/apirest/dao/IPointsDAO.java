package com.gisapp.springboot.backend.apirest.dao;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;

public interface IPointsDAO {

	/**
	 * Method to find the points by user
	 * @param userId
	 * @return 
	 */
	public Object findPointsByUserId(String userId);
	
	/**
	 * Method to find the points within an area
	 * @param polygon
	 * @return List<PointsEntity>
	 */
	List<PointsEntity> findPointsIntoAPolygon(PolygonEntity polygon);
}
