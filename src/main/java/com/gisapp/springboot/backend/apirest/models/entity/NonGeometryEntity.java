package com.gisapp.springboot.backend.apirest.models.entity;

import javax.persistence.Column;

/**
 * Class to convert the json that comes from the front-end to the GeometryEntity
 * through the wkt converter
 * @author cftej
 *
 */
public class NonGeometryEntity {
	
	private String userId;

	private String pointName;

	private String geom;

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
