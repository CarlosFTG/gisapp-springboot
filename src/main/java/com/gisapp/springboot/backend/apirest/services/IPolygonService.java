package com.gisapp.springboot.backend.apirest.services;

import java.util.List;

import org.json.JSONException;

import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.io.ParseException;

public interface IPolygonService {

	List<com.gisapp.springboot.backend.apirest.models.bean.BufferBean> createBuffer(List<NonGeometryEntity> pointList) throws JSONException, ParseException;

}
