package com.gisapp.springboot.backend.apirest.models.services;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.io.ParseException;

public interface IClienteService {
	
	public List<GeometryEntity> findAll();
	
	public void save(GeometryEntity geometry);
	
	public NonGeometryEntity findById(Long id) throws ParseException;
	
	public void delete(GeometryEntity cliente);
	
	public List<NonGeometryEntity> findPointsByUserId(String userId) throws ParseException;

}
