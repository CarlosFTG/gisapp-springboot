package com.gisapp.springboot.backend.apirest.models.bean;

import com.vividsolutions.jts.geom.Coordinate;

public class BufferBean {

	private Long id;
	
	private Coordinate[] coordinates;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Coordinate[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate[] coordinates) {
		this.coordinates = coordinates;
	}
	
	
}
