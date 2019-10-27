package com.gisapp.springboot.backend.apirest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gisapp.springboot.backend.apirest.dao.ILinesGenericDAO;
import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;
import com.gisapp.springboot.backend.apirest.services.ILineService;

@Service
public class LineServiceImpl implements ILineService {
	
	@Autowired
	private ILinesGenericDAO linesGenericDao;
	
	@Override
	@Transactional(readOnly = true)
	public void saveLine(LineEntity line) {

		linesGenericDao.save(line);
	}
}
