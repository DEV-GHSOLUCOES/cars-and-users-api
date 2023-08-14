package com.example.cars.and.users.api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cars.and.users.api.exceptions.EmailAlreadyExistsException;
import com.example.cars.and.users.api.exceptions.LoginAlreadyExistsException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Autowired
	private MessageSource messageSource;

	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Erro> erros = criarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({EmailAlreadyExistsException.class})
	public ResponseEntity<Object> handleEmailAlreadyExistsException( EmailAlreadyExistsException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("email.exists", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}	
	
	
	@ExceptionHandler({LoginAlreadyExistsException.class})
	public ResponseEntity<Object> handleLoginAlreadyExistsException( LoginAlreadyExistsException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("login.exists", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}	
	
	
	
	private List<Erro> criarListaErros(BindingResult bindingResult) {

		List<Erro> erros = new ArrayList<>();
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String menssagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String menssagemDesenvolvedor = fieldError.toString();
			erros.add(new Erro(menssagemUsuario, menssagemDesenvolvedor));

		}

		return erros;
	}

	public static class Erro {

		private String menssagemUsuario;
		private String menssagemDesenvolvedor;

		public Erro(String menssagemUsuario, String menssagemDesenvolvedor) {

			this.menssagemUsuario = menssagemUsuario;
			this.menssagemDesenvolvedor = menssagemDesenvolvedor;
		}

		public String getMenssagemUsuario() {
			return menssagemUsuario;
		}

		public void setMenssagemUsuario(String menssagemUsuario) {
			this.menssagemUsuario = menssagemUsuario;
		}

		public String getMenssagemDesenvolvedor() {
			return menssagemDesenvolvedor;
		}

		public void setMenssagemDesenvolvedor(String menssagemDesenvolvedor) {
			this.menssagemDesenvolvedor = menssagemDesenvolvedor;
		}

	}

}
