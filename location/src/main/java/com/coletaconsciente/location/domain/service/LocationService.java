package com.coletaconsciente.location.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coletaconsciente.location.domain.exception.LocationNotFoundException;
import com.coletaconsciente.location.domain.model.Location;
import com.coletaconsciente.location.domain.repository.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	public List<Location> findAll() {
		return locationRepository.findAll();
	}
	
	public Location findById(Long locationId) {
		return locationRepository
				.findById(locationId)
				.orElseThrow(() -> new LocationNotFoundException(locationId));
	}

	@Transactional
	public Location save(Location location) {
		return locationRepository.save(location);
	}
	
	public Location update(Long locationId, Location location) {
		Location persistedLocation =findById(locationId);
		BeanUtils.copyProperties(location, persistedLocation, "id", "registrationDate", "updateDate");

		return save(persistedLocation);
	}

	@Transactional
	public void remove(Long locationId) {
		try {
			locationRepository.deleteById(locationId);
		} catch (EmptyResultDataAccessException e) {
			throw new LocationNotFoundException(locationId);
		} 
	}
}