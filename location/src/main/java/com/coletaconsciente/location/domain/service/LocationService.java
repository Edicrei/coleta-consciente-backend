package com.coletaconsciente.location.domain.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import com.coletaconsciente.location.domain.exception.LocationNotFoundException;
import com.coletaconsciente.location.domain.exception.ValidationException;
import com.coletaconsciente.location.domain.model.Location;
import com.coletaconsciente.location.domain.repository.LocationRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private SmartValidator validator;
	
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
		Location persistedLocation = findById(locationId);
		BeanUtils.copyProperties(location, persistedLocation, "id", "registrationDate", "updateDate");

		return save(persistedLocation);
	}
	
	public Location partialUpdate(Long locationId, Map<String, Object> fields, HttpServletRequest request) {
		Location persistedLocation = findById(locationId);
		merge(fields, persistedLocation, request);
		validate(persistedLocation, "restaurante");
		return save(persistedLocation);
	}

	private void merge(Map<String, Object> sourceData, Location destinationLocation, HttpServletRequest request) {
		
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
		
		try {
			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		
			Location sourceLocation = objectMapper.convertValue(sourceData, Location.class);
	
			sourceData.forEach((propertyName, propertyValue) -> {
				Field field = ReflectionUtils.findField(Location.class, propertyName);
				field.setAccessible(true);
				Object newValue = ReflectionUtils.getField(field, sourceLocation);
				ReflectionUtils.setField(field, destinationLocation, newValue);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
	}
	
	private void validate(Location location, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(location, objectName);
		validator.validate(location, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidationException(bindingResult);
		}
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