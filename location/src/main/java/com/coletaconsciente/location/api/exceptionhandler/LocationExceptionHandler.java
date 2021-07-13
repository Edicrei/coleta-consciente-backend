package com.coletaconsciente.location.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.coletaconsciente.location.domain.exception.LocationNotFoundException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class LocationExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static final String GENERIC_ERROR_MESSAGE = "An unexpected internal system error has occurred. "
			+ "Please try again and if the problem persists, contact system administrator.";
	
	@ExceptionHandler(LocationNotFoundException.class)
	public ResponseEntity<?> handleLocationNotFound(LocationNotFoundException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ProblemType problemType = ProblemType.LOCATION_NOT_FOUND;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleUncaught(Exception ex, WebRequest request) {
	
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.SYSTEM_ERROR;
		String detail = GENERIC_ERROR_MESSAGE;
		
	    ex.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
		} 
		
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = "The request body is invalid. Check syntax error.";
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(GENERIC_ERROR_MESSAGE)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("The '%s' property received value '%s', which is an invalid type. "
				+ "Enter a compatible value for type '%s'",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(GENERIC_ERROR_MESSAGE)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = joinPath(ex.getPath());
		ProblemType problemType = ProblemType.INCOMPREHENSIBLE_MESSAGE;
		String detail = String.format("The property '%s' does not exist. "
				+ "Please correct or remove this property", path);
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(GENERIC_ERROR_MESSAGE)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		if (ex instanceof MethodArgumentTypeMismatchException ) {
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		} 
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.INVALID_PARAMETER;
		String detail = String.format("The URL parameter '%s' received the value '%s', "
				+ "which is an invalid type. Correct and enter a value compatible with type %s.", 
				ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(GENERIC_ERROR_MESSAGE)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
		String detail = String.format("The resource '%s' does not exist", ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(GENERIC_ERROR_MESSAGE)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request); 
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ProblemType problemType = ProblemType.INVALID_DATA;
		String detail = "One or more fields are invalid. Fill it in correctly and try again";
		
		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
				.map(objectError -> {
					String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
					
					String name = objectError.getObjectName();
					
					if (objectError instanceof FieldError) {
						name = ((FieldError) objectError).getField();
					}
					
					return Problem.Object.builder()
						.name(name)
						.userMessage(message)
						.build();
				})
				.collect(Collectors.toList());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.objects(problemObjects)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.title(status.getReasonPhrase())
					.status(status.value())
					.userMessage(GENERIC_ERROR_MESSAGE)
					.build();
		} else if (body instanceof String) {
			body = Problem.builder()
					.timestamp(LocalDateTime.now())
					.title(status.getReasonPhrase())
					.status(status.value())
					.userMessage(GENERIC_ERROR_MESSAGE)
					.build();
		} 
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
		return Problem.builder()
				.timestamp(LocalDateTime.now())
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}

	private String joinPath(List<Reference> references) {
		return references.stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
	}
}