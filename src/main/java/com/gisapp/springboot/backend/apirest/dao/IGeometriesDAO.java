package com.gisapp.springboot.backend.apirest.dao;

public interface IGeometriesDAO {

	public Object findPointsByUserId(String userId);
	public Object findPointsIntoAPolygon();
}
