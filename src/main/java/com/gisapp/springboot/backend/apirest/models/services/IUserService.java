package com.gisapp.springboot.backend.apirest.models.services;

import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

public interface IUserService {

	public String login(LoginEntity user);
	
	public void save(UserEntity user);
}
