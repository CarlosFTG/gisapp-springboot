package com.gisapp.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.gisapp.springboot.backend.apirest.models.entity.TempPolygonEntity;

public interface ITempPolygonGenericDAO extends CrudRepository<TempPolygonEntity, Long>{

}
