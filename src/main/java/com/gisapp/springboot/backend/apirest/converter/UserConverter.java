package com.gisapp.springboot.backend.apirest.converter;

import org.json.JSONException;

import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.vividsolutions.jts.io.ParseException;

public class UserConverter {

	public static UserBean convertToUserBean(UserEntity userEntity)
			throws ParseException, JSONException {

		UserBean userBean = new UserBean();
		
		userBean.setId(userEntity.getId());
		userBean.setUserName(userEntity.getUserName());
		userBean.setSurname(userEntity.getSurname());
		userBean.setEmail(userEntity.getEmail());
		userBean.setPointsList(PointsConverter.convertToGeometryBeanList(userEntity.getPointsList()));
		userBean.setPolygonsList(PolygonsConverter.convertToPolygonBeanList(userEntity.getPolygonsList()));
		userBean.setLinesList(LinesConverter.convertToLineBeanList(userEntity.getLinesList()));
		
		return userBean;
	}
}
