package com.gisapp.springboot.backend.apirest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.gisapp.springboot.backend.apirest.dao.IPointsDAO;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.vividsolutions.jts.geom.Geometry;

@Repository
public class PointsDAO implements IPointsDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public Object findPointsByUserId(String userId) {

		StringBuffer sb = new StringBuffer();
		sb.append("SELECT user FROM UserEntity user " + "WHERE id in (:userId) ");

		Query q = em.createQuery(sb.toString());

		q.setParameter("userId", Long.parseLong(userId));

		UserEntity userFound = new UserEntity();

		try {
			userFound = (UserEntity) q.getSingleResult();
		} catch (NoResultException nre) {
		}
		return userFound;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PointsEntity> findPointsIntoAPolygon(PolygonEntity polygon) {

		StringBuffer sb = new StringBuffer();
		sb.append("select p from PointsEntity p " + "where p.userId in (:userId) "
				+ "and within(p.coordinates, :polygon) = true");

		Query q = em.createQuery(sb.toString());
		q.setParameter("polygon", polygon.getGeom());
		q.setParameter("userId", polygon.getUserId());

		List<PointsEntity> pointsFound = new ArrayList<PointsEntity>();

		try {
			pointsFound = q.getResultList();
		} catch (NoResultException nre) {
		}

		return pointsFound;

	}
}
