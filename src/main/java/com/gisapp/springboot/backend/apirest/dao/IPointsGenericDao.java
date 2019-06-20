package com.gisapp.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;

public interface IPointsGenericDao extends CrudRepository<PointsEntity, Long>{

}
