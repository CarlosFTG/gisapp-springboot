package com.gisapp.springboot.backend.apirest.dao;

import org.springframework.data.repository.CrudRepository;

import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

public interface IUserGenericDAO extends CrudRepository<UserEntity, Long>{

}
