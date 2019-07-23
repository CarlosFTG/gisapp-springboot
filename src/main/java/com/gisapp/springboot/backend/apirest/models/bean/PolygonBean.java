package com.gisapp.springboot.backend.apirest.models.bean;

import org.json.JSONObject;

public class PolygonBean {
	
	private Long id;

	private Long userId;

	private String polygonName;
	
	private String userEmail;

	private JSONObject geom;
	
	private boolean isBuffer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPolygonName() {
		return polygonName;
	}

	public void setPolygonName(String pointName) {
		this.polygonName = pointName;
	}

	public JSONObject getGeom() {
		return geom;
	}

	public void setGeom(JSONObject geom) {
		this.geom = geom;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public boolean isBuffer() {
		return isBuffer;
	}

	public void setBuffer(boolean isBuffer) {
		this.isBuffer = isBuffer;
	}
	
	
}