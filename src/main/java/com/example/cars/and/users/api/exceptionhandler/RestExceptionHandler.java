package com.example.cars.and.users.api.exceptionhandler;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.cars.and.users.api.controller.ErrorResponse;
import com.example.cars.and.users.api.exceptions.EmailAlreadyExistsException;
import com.example.cars.and.users.api.exceptions.LicensePlateAlreadyExistsExeception;
import com.example.cars.and.users.api.exceptions.LoginAlreadyExistsException;
import com.example.cars.and.users.api.exceptions.UserNotFoundException;


@RestControllerAdvice
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ErrorResponse errorResponse;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErrorResponse> erros = this.errorResponse.criarListaErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({EmailAlreadyExistsException.class})
	public ResponseEntity<Object> handleEmailAlreadyExistsException( EmailAlreadyExistsException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("email.exists", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = HttpStatus.BAD_REQUEST.toString();
		List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
		
		
	}	
	
	
	
	
	@ExceptionHandler({LicensePlateAlreadyExistsExeception.class})
	public ResponseEntity<Object> handleLicensePlateAlreadyExistsExeception( LicensePlateAlreadyExistsExeception ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("license.exists", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = HttpStatus.BAD_REQUEST.toString();
		List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}	
	
	
		
	
	@ExceptionHandler({NoSuchElementException.class})
	public ResponseEntity<Object> handleNoSuchElementException( NoSuchElementException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("car.notfound", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = HttpStatus.INTERNAL_SERVER_ERROR.toString();
		List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}	
	
	@ExceptionHandler({LoginAlreadyExistsException.class})
	public ResponseEntity<Object> handleLoginAlreadyExistsException( LoginAlreadyExistsException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("login.exists", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = HttpStatus.BAD_REQUEST.toString();
		List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}	
	
		
	@ExceptionHandler({IllegalStateException.class})
	public ResponseEntity<Object> handleIllegalStateException( IllegalStateException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("conflict", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = HttpStatus.CONFLICT.toString();
		List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<Object> handleUserNotFoundException( UserNotFoundException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("user.notfound", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = HttpStatus.NOT_FOUND.toString();
		List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	@ExceptionHandler({InternalAuthenticationServiceException.class})
	public ResponseEntity<Object> handleInternalAuthenticationServiceException( InternalAuthenticationServiceException ex, WebRequest request) {
		String menssagemUsuario = messageSource.getMessage("login.invalid", null, LocaleContextHolder.getLocale());
		String menssagemDesenvolvedor = HttpStatus.UNAUTHORIZED.toString();
		List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}
	
	
	 @ExceptionHandler(AuthenticationException.class)
	    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
		 String menssagemUsuario = messageSource.getMessage("unauthorized", null, LocaleContextHolder.getLocale());
			String menssagemDesenvolvedor = HttpStatus.UNAUTHORIZED.toString();
			List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
			
			return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	    }

	    @ExceptionHandler(AccessDeniedException.class)
	    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
	    	
	    	 String menssagemUsuario = messageSource.getMessage("access.denied", null, LocaleContextHolder.getLocale());
				String menssagemDesenvolvedor = HttpStatus.FORBIDDEN.toString();
				List<ErrorResponse> erros = Arrays.asList(new ErrorResponse(menssagemUsuario, menssagemDesenvolvedor));
				
				return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	    	
	    	
	        
	    }
	
		
	
}
