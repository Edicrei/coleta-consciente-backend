package com.coletaconsciente.location.domain.exception;

public class LocationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private static final String LOCATION_NOT_FOUND_EXCEPTION = "There is no location register with code %d";

	public LocationNotFoundException(String mensagem) {
		super(mensagem);
	}
	
	public LocationNotFoundException(Long id) {
		this(String.format(LOCATION_NOT_FOUND_EXCEPTION, id));
	}
	
	public LocationNotFoundException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}