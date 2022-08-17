package com.devsuperior.bds02.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityServices {

	@Autowired
	private CityRepository repository;
	
	@Transactional
	public List<CityDTO> findAll() {
	return repository.findAll(Sort.by("name")).stream().map(c -> new CityDTO(c)).collect(Collectors.toList());	
		
	}

	@Transactional
	public CityDTO insert(CityDTO cityDTO) {
		City city = new City();
		city.setName(cityDTO.getName());
		repository.save(city);
		return new CityDTO(city);
	}

}
