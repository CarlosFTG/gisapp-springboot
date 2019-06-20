package com.gisapp.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;

public interface IPolygonsGenericDAO extends CrudRepository<PolygonEntity, Long>{

}
