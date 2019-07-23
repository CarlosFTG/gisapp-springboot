package com.gisapp.springboot.backend.apirest.dao;

import java.util.List;

import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.geom.Polygon;

public interface IPolygonDAO {

	List<Polygon> createBuffer(NonGeometryEntity buffer);

}
