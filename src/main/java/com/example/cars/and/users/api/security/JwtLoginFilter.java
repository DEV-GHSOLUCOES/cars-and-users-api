package com.example.cars.and.users.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.cars.and.users.api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

/*Estabelece o nosso genrenciador  de token*/
public class JwtLoginFilter  extends  AbstractAuthenticationProcessingFilter{

	/* Configurando o gerenciador de autenticacao */
	protected JwtLoginFilter(String url, AuthenticationManager authenticationManager) {
		
		/* Obriga a atutenticar a url */
		super(new AntPathRequestMatcher(url));
		
		/* Gerenciador de autenticacao */
		setAuthenticationManager(authenticationManager);
	
	}

	/* Retorna o user ao processar a autenticacao */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		/* Esta pegando o token para validar */
		User user  =  new ObjectMapper().readValue(request.getInputStream(), User.class);
		
		/* Retorna o user login, senha e acessos */
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
	}

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
    		Authentication authResult) throws IOException, ServletException {
    	
    	new JwtTokenAutenticacaoService().addAuthentication(response, authResult.getName());
    }

   
}
