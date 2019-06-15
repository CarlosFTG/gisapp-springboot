package com.gisapp.springboot.backend.apirest.services;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;

public interface IGeometryService {

	public List<GeometryEntity> findAll();

	public void save(GeometryEntity geometry);

	public void delete(GeometryEntity cliente);

}
