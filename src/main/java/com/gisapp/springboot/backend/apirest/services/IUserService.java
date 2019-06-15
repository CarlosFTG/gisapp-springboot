package com.gisapp.springboot.backend.apirest.services;

import org.json.JSONException;

import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.vividsolutions.jts.io.ParseException;

public interface IUserService {

	public UserBean login(LoginEntity user) throws ParseException, JSONException;
	
	public void save(UserEntity user);
}
