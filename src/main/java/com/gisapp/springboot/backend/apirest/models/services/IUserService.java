package com.gisapp.springboot.backend.apirest.models.services;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

public interface IUserService {

	public UserEntity login(LoginEntity user);
	
	public void save(UserEntity user);
}
