package com.gisapp.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;

public interface ILinesGenericDAO extends CrudRepository<LineEntity, Long>{

}
