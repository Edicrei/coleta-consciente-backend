package com.coletaconsciente.location.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Embeddable
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Valid
	@NotNull
	@Embedded
	private Coordinates coordinates;
	
	@NotBlank
	@Column(nullable = false)
	private String street;
	
	@NotBlank
	@Column(nullable = false)
	private String number;
	
	private String complement;
}