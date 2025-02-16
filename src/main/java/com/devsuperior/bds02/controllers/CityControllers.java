package com.devsuperior.bds02.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.bds02.Services.CityServices;
import com.devsuperior.bds02.dto.CityDTO;

@RestController
@RequestMapping(value=("/cities"))
public class CityControllers {
	
	@Autowired
	private CityServices service;
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll(){
		List<CityDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<CityDTO> insert(@RequestBody CityDTO cityDTO){
		
		cityDTO = service.insert(cityDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cityDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(cityDTO);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		
		try {
			service.delete(id);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.badRequest().build();
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}catch (Exception e) {
			System.err.println(e.toString());
		}
		return ResponseEntity.noContent().build();
		}
	
	

}
