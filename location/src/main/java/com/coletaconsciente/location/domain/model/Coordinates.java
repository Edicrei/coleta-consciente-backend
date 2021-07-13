package com.coletaconsciente.location.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Embeddable
public class Coordinates {
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal latitude;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal longitude;
}