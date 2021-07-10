package com.coletaconsciente.location.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Embeddable
public class Coordinates implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private BigDecimal latitude;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal longitude;
}