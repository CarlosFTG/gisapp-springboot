package com.gisapp.springboot.backend.apirest.models.bean;

import org.json.JSONObject;

public class LineBean {
	
	private Long id;

	private Long userId;

	private String lineName;

	private JSONObject geom;

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

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String pointName) {
		this.lineName = pointName;
	}

	public JSONObject getGeom() {
		return geom;
	}

	public void setGeom(JSONObject geom) {
		this.geom = geom;
	}
	
	
}
