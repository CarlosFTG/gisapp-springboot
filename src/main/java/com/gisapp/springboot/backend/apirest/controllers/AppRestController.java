package com.gisapp.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gisapp.springboot.backend.apirest.converter.LinesConverter;
import com.gisapp.springboot.backend.apirest.converter.PointsConverter;
import com.gisapp.springboot.backend.apirest.converter.PolygonsConverter;
import com.gisapp.springboot.backend.apirest.models.bean.BufferBean;
import com.gisapp.springboot.backend.apirest.models.bean.PointBean;
import com.gisapp.springboot.backend.apirest.models.bean.PolygonBean;
import com.gisapp.springboot.backend.apirest.models.bean.UserBean;
import com.gisapp.springboot.backend.apirest.models.entity.PointsEntity;
import com.gisapp.springboot.backend.apirest.models.entity.PolygonEntity;
import com.gisapp.springboot.backend.apirest.models.entity.LineEntity;
import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.gisapp.springboot.backend.apirest.services.IPointService;
import com.gisapp.springboot.backend.apirest.services.IPolygonService;
import com.gisapp.springboot.backend.apirest.services.IUserService;
import com.vividsolutions.jts.io.ParseException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AppRestController {

	@Autowired
	private IPointService pointService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IPolygonService polygonService;
	
	ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/clientes")
	public List<PointsEntity> index() {
		return pointService.findAll();
	}

	@PostMapping("/geometries/insertPoint")
	@ResponseStatus(HttpStatus.CREATED)
	public PointsEntity insertPoint(@RequestBody NonGeometryEntity geometry) throws ParseException {

		PointsEntity geomToSave = new PointsEntity();
		this.pointService.savePoint(PointsConverter.convertToPointEntity(geometry));
		return geomToSave;
	}
	
	@PostMapping("/geometries/insertPolygon")
	@ResponseStatus(HttpStatus.CREATED)
	public PolygonEntity insertPolygon(@RequestBody NonGeometryEntity polygonBean) throws ParseException {

		PolygonEntity geomToSave = new PolygonEntity();
		this.pointService.savePolygons(PolygonsConverter.convertToGeometryEntity(polygonBean));
		return geomToSave;
	}
	
	@PostMapping("/geometries/insertLine")
	@ResponseStatus(HttpStatus.CREATED)
	public LineEntity insertLine(@RequestBody NonGeometryEntity lineBean) throws ParseException {

		LineEntity geomToSave = new LineEntity();
		this.pointService.saveLine(LinesConverter.convertToGeometryEntity(lineBean));
		return geomToSave;
	}
	
	@PostMapping("/geometries/findPointByUserId")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<UserBean> findPointByUserId(@RequestBody String userId) throws ParseException, JSONException {
		
		return ResponseEntity.ok(this.pointService.findFeaturesByUserId(userId));
	}
	
	@PostMapping("/geometries/findPointsIntoAPolygon")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<Map<String, String>>> findPointsIntoAPolygon(@RequestBody String polygon) throws ParseException, JSONException, IOException {
		
		return ResponseEntity.ok(this.pointService.findPointsIntoAPolygon(polygon));
	}

	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public PointsEntity update(@RequestBody PointsEntity cliente, @PathVariable Long id) {

		return null;
	}

//	@DeleteMapping("/clientes/{id}")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	public void delete(@PathVariable Long id) {
//		// GeometryEntity currentCliente = this.clienteService.findById(id);
//		// this.clienteService.delete(currentCliente);
//	}

	@PostMapping("/users/login")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<UserBean> login(@RequestBody LoginEntity user) throws ParseException, JSONException, JsonProcessingException {

		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Type", "application/json; charset=utf-8");
		 
		return ResponseEntity.ok(this.userService.login(user)); 

			
		}

	@PostMapping("/users/createUser")
	@ResponseStatus(HttpStatus.CREATED)
	public PointsEntity createUser(@RequestBody UserEntity user) throws ParseException {

		this.userService.save(user);
		return null;
	}
	
	@PostMapping("/geometries/removePoints")
	@ResponseStatus(HttpStatus.CREATED)
	public void removePoint(@RequestBody List<NonGeometryEntity> geometryList) throws ParseException {

		this.pointService.removePoint(PointsConverter.convertToPointEntityList(geometryList));
	}
	
	@PostMapping("/geometries/createBuffer")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<List<BufferBean>> createBuffer(@RequestBody List<NonGeometryEntity> pointList) throws JSONException, ParseException {
		
		//buffer.setRadioBuffer("0.005");
		
		return ResponseEntity.ok(this.polygonService.createBuffer(pointList));

	}
	
}
