package com.gisapp.springboot.backend.apirest.models.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.converter.GeometryEntityConverter;
import com.gisapp.springboot.backend.apirest.models.dao.IClienteDao;
import com.gisapp.springboot.backend.apirest.models.dao.IGeometriesDAO;
import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.vividsolutions.jts.io.ParseException;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;

	@Autowired
	private IGeometriesDAO geometriesDAO;

	@Override
	@Transactional(readOnly = true)
	public List<GeometryEntity> findAll() {
		return (List<GeometryEntity>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(GeometryEntity geometry) {
		clienteDao.save(geometry);
	}

	@Override
	@Transactional(readOnly = true)
	public NonGeometryEntity findById(Long id) throws ParseException {
		GeometryEntity gE = clienteDao.findById(id).orElse(null);
		return null;

	}

	@Override
	@Transactional
	public void delete(GeometryEntity cliente) {
		clienteDao.delete(cliente);

	}

	@Transactional
	public List<NonGeometryEntity> findPointsByUserId(String userId) throws ParseException {

		List<GeometryEntity> gE = geometriesDAO.findPointsByUserId(userId);
		return GeometryEntityConverter.convertToNonGeometryEntity(gE);
	}



}
