package com.gisapp.springboot.backend.apirest.models.entity;

/**
 * Class to convert the json that comes from the front-end to the GeometryEntity
 * through the wkt converter
 * @author cftej
 *
 */
public class NonGeometryEntity {
	
	private String userId;
	
	private String userEmail;

	private String geometryName;

	private String geom;
	
	private String facility;
	
	private String radioBuffer;

	public String getGeom() {
		return geom;
	}

	public void setGeom(String geom) {
		this.geom = geom;
	}

	public String getPointName() {
		return geometryName;
	}

	public void setPointName(String pointName) {
		this.geometryName = pointName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getRadioBuffer() {
		return radioBuffer;
	}

	public void setRadioBuffer(String radioBuffer) {
		this.radioBuffer = radioBuffer;
	}
}
