package com.gisapp.springboot.backend.apirest.dao;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.TempPolygonEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

public interface IGeometriesDAO {

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
	List<PointsEntity> findPointsIntoAPolygon(TempPolygonEntity polygon);
	
	/**
	 * Method to find the points by user and coordinates
	 * @param pointsList
	 * @return
	 */
	List<PointsEntity> findPointsByUserIdAndCoords(List<PointsEntity> pointsList);
}
