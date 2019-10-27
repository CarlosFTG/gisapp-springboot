package com.gisapp.springboot.backend.apirest.dao;

import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.vividsolutions.jts.geom.Polygon;

public interface IPolygonDAO {

	Polygon createBuffer(NonGeometryEntity pointList);

	PolygonEntity findPolygonById(Long polygonId);

}
