package com.example.cars.and.users.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


@org.springframework.stereotype.Component
public class ErrorResponse {
	
	
	@Autowired
	private MessageSource messageSource;

	private String message;
	private String errorCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public ErrorResponse() {
		// TODO Auto-generated constructor stub
	}
	
		
	
	public ErrorResponse(String message, String errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public List<ErrorResponse> criarListaErros(BindingResult bindingResult) {

		List<ErrorResponse> erros = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String errorCode = fieldError.toString();
			erros.add(new ErrorResponse(message, errorCode));

		}

		return erros;
	}

}
