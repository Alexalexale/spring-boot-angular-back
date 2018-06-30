package br.com.teste.spring_angular_back.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.teste.spring_angular_back.services.exception.ObjectNotFoundException;

@ControllerAdvice
public class ResoucerExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandardError standardError = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Not Found", e.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError standardError = new ValidationError(System.currentTimeMillis(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação.", "Erro de validação.",
				request.getRequestURI());

		e.getBindingResult().getFieldErrors()
				.forEach(fe -> standardError.addError(fe.getField(), fe.getDefaultMessage()));

		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(standardError);

	}

}