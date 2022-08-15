package com.devsuperior.bds02.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.bds02.Services.EventServices;
import com.devsuperior.bds02.dto.EventDTO;

@RestController
@RequestMapping(value="/events")
public class EventControllers {
	
	@Autowired
	private EventServices service;
	
	@PutMapping(value="{id}")
	public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO event) {
		try {
			event = service.update(event, id);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(event);
	}

}
