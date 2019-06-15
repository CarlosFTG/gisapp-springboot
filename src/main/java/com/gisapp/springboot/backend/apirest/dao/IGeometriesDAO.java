package com.gisapp.springboot.backend.apirest.dao;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;

public interface IGeometriesDAO {

	List<GeometryEntity> findPointsByUserId(String userId);
}
