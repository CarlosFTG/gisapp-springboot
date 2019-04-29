package com.gisapp.springboot.backend.apirest.models.dao;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;

public interface IGeometriesDAO {

	List<GeometryEntity> findPointsByUserId(String userId);
}
