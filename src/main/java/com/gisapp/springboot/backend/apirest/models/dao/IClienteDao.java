package com.gisapp.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;

public interface IClienteDao extends CrudRepository<GeometryEntity, Long>{

}
