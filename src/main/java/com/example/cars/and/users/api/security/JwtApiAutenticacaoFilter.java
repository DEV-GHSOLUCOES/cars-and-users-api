package com.example.cars.and.users.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

/*filtro onde todas as requisicoes seram capturadas para autenticar*/
public class JwtApiAutenticacaoFilter  extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		/* Estabelece autenticacao para requisicao */
		Authentication authentication  =  new JwtTokenAutenticacaoService().getAuthentication((HttpServletRequest)request);
		
		/* Coloca o processo de autenticacao no spring security */
		SecurityContextHolder.getContext().setAuthentication(authentication);	
		
		/* Continua o processo */
		chain.doFilter(request, response);
		
	}

}
