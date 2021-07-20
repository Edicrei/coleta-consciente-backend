package com.coletaconsciente.location.api.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.coletaconsciente.location.domain.model.Location;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "Location Controller", notes = "API for Location service")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 400, message = "error, verify your request and try again")
})
public interface LocationController {

	public List<Location> findAll();
	public Location findById(@PathVariable("locationId") Long id);
	public Location save(@RequestBody @Valid Location location);
	public Location update(@PathVariable Long locationId, @RequestBody @Valid Location location);
	public Location partialUpdate(@PathVariable Long locationId, @RequestBody Map<String, Object> fields, HttpServletRequest request);
	public void remove(@PathVariable Long locationId);
}
