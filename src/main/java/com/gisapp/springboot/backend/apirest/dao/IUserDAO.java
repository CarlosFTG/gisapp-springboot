package com.gisapp.springboot.backend.apirest.dao;

import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;

public interface IUserDAO {

	/**
	 * Method used to login and to check if the user exists during the registration process
	 * @param user
	 * @return
	 */
	public Object login(UserEntity user);
	
}
