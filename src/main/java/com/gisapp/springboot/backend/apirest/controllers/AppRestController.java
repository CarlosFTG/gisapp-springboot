package com.gisapp.springboot.backend.apirest.controllers;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gisapp.springboot.backend.apirest.converter.GeometryEntityConverter;
import com.gisapp.springboot.backend.apirest.models.entity.GeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.LoginEntity;
import com.gisapp.springboot.backend.apirest.models.entity.NonGeometryEntity;
import com.gisapp.springboot.backend.apirest.models.entity.UserEntity;
import com.gisapp.springboot.backend.apirest.models.services.IGeometryService;
import com.gisapp.springboot.backend.apirest.models.services.IUserService;
import com.vividsolutions.jts.io.ParseException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class AppRestController {

	@Autowired
	private IGeometryService geometryService;

	@Autowired
	private IUserService userService;

	@GetMapping("/clientes")
	public List<GeometryEntity> index() {
		return geometryService.findAll();
	}

	@GetMapping("geometries/findByUserId/{id}")
	public NonGeometryEntity show(@PathVariable Long id) throws ParseException {
		return this.geometryService.findById(id);
	}

	@PostMapping("geometries/findPointsByUserId")
	@ResponseStatus(HttpStatus.FOUND)
	public List<NonGeometryEntity> findPointsByUserId(@RequestBody String body) throws ParseException {

		String[] bodyToSplit = body.split("=");
		String userId = bodyToSplit[1];

		return this.geometryService.findPointsByUserId(userId);
	}

	@PostMapping("/geometries/insertGeometry")
	@ResponseStatus(HttpStatus.CREATED)
	public GeometryEntity create(@RequestBody NonGeometryEntity geometry) throws ParseException {

		GeometryEntity geomToSave = new GeometryEntity();
		this.geometryService.save(GeometryEntityConverter.convertToGeometryEntity(geometry));
		return geomToSave;
	}

	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public GeometryEntity update(@RequestBody GeometryEntity cliente, @PathVariable Long id) {

		return null;
	}

	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		// GeometryEntity currentCliente = this.clienteService.findById(id);
		// this.clienteService.delete(currentCliente);
	}

	@PostMapping("/users/login")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<Object>  login(@RequestBody LoginEntity user) throws ParseException, JSONException {

		 HttpHeaders headers = new HttpHeaders();
		 headers.add("Content-Type", "application/json; charset=utf-8");
		
		UserEntity serviceResult = this.userService.login(user);

		JSONObject jsonToClient = new JSONObject();

		if (serviceResult.getUserName() == null && serviceResult.getEmail()==null) {
			 jsonToClient.put("user name", null);
			jsonToClient.put("email", null);
		} else if(serviceResult.getUserName() != null && serviceResult.getEmail()!=null) {

			jsonToClient.put("user name", serviceResult.getUserName());
			jsonToClient.put("email", serviceResult.getEmail());
			
		}
		
		return new ResponseEntity<Object>(jsonToClient, HttpStatus.OK);	}

	@PostMapping("/users/createUser")
	@ResponseStatus(HttpStatus.CREATED)
	public GeometryEntity createUser(@RequestBody UserEntity user) throws ParseException {

		this.userService.save(user);
		return null;
	}
}
