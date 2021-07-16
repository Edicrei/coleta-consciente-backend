package com.coletaconsciente.location.api.controller.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coletaconsciente.location.api.controller.impl.LocationControllerImpl;
import com.coletaconsciente.location.domain.model.Location;
import com.coletaconsciente.location.domain.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationControllerImpl {

	@Autowired
	private LocationService locationService;

	@GetMapping
	public List<Location> findAll() {
		return locationService.findAll();
	}

	@GetMapping("/{locationId}")
	public Location findById(@PathVariable("locationId") Long id) {
		return locationService.findById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Location save(@RequestBody @Valid Location location) {
		return locationService.save(location);
	}

	@PutMapping("/{locationId}")
	public Location update(@PathVariable Long locationId, @RequestBody @Valid Location location) {
		return locationService.update(locationId, location);
	}
	
	@PatchMapping("/{locationId}")
	public Location partialUpdate(@PathVariable Long locationId, @RequestBody Map<String, Object> fields, HttpServletRequest request) {
		return locationService.partialUpdate(locationId, fields, request);
	}

	@DeleteMapping("/{locationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Long locationId) {
		locationService.remove(locationId);
	}
}