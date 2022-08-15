package com.devsuperior.bds02.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventServices {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Transactional
	public EventDTO update(EventDTO eventDTO, Long id) throws NotFoundException {
		
		Event event = new Event();
		City city = cityRepository.getOne(eventDTO.getCityId());
		
		if(!eventRepository.existsById(id))
			throw new NotFoundException();
		event.setId(id);
		event.setCity(city);
		event.setDate(eventDTO.getDate());
		event.setName(eventDTO.getName());
		event.setUrl(eventDTO.getUrl());
		
		event = eventRepository.save(event);
		System.err.println("SAVED");
		return new EventDTO(event);
	}

}
