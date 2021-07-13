package com.coletaconsciente.location.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	INVALID_DATA("Invalid Data", "/invalid-data"),
	INCOMPREHENSIBLE_MESSAGE("Incomprehensible Message","/incomprehensible-message"),
	INVALID_PARAMETER("Invalid Parameter", "/invalid-parameter"),
	LOCATION_NOT_FOUND("Location Not Found", "/location-not-found"),
	SYSTEM_ERROR("System Error", "/system-error"),
	RESOURCE_NOT_FOUND("Resource Not Found", "/resource-not-found");
	
	private String title;
	private String uri;
	
	ProblemType(String title, String path) {
		this.title = title;
		this.uri = "https://coletaconsciente.com" + path;
	}
}