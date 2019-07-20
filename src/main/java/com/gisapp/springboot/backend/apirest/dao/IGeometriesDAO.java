package com.gisapp.springboot.backend.apirest.dao;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.TempPolygonEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

public interface IGeometriesDAO {

	public Object findPointsByUserId(String userId);
	List<PointsEntity> findPointsIntoAPolygon(TempPolygonEntity polygon);
}
