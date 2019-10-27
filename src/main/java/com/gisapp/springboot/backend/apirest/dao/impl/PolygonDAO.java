package com.gisapp.springboot.backend.apirest.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.gisapp.springboot.backend.apirest.dao.IPolygonDAO;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.vividsolutions.jts.geom.Polygon;

@Repository
public class PolygonDAO implements IPolygonDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Polygon createBuffer(NonGeometryEntity point) {

		StringBuffer sb = new StringBuffer();
		sb.append("select buffer(p.coordinates, " + "(:radio)) from PointsEntity p " + "where userId in (:userId) "
				+ "and id in (:pointId)");

		Polygon polygonEntity = null;

		Query q = em.createQuery(sb.toString());
		// q.setParameter("radio", Double.parseDouble(point.getRadioBuffer()) );
		q.setParameter("radio", Double.parseDouble("0.005"));
		q.setParameter("userId", Long.parseLong(point.getUserId()));
		q.setParameter("pointId", Long.parseLong(point.getPointId()));
		polygonEntity = (Polygon) q.getSingleResult();

		return polygonEntity;
	}

	@Override
	public PolygonEntity findPolygonById(Long polygon_id) {

		StringBuffer sb = new StringBuffer();

		PolygonEntity polygonFound = new PolygonEntity();

		sb.append("select p from PolygonEntity p " + "where id in (:polygonId) ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("polygonId", polygon_id);

		return polygonFound = (PolygonEntity) q.getSingleResult();

	}

	
}
