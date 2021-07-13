package com.coletaconsciente.location.domain.exception;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private BindingResult bindingResult;
}
