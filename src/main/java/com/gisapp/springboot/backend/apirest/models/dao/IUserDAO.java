package com.gisapp.springboot.backend.apirest.models.dao;

import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

public interface IUserDAO {

	public Object login(UserEntity user);
	
}
