package com.example.cars.and.users.api.security;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.ApplicationContextLoad;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.IOException;

@Service
@Component
public class JwtTokenAutenticacaoService {
	
	
	/*
	 * @Autowired //private UserRepository userRepository;
	 */	
	
	/*Tempo de validade do token 2 dias*/
	private static final long EXPIRATION_TIME = 172800000;
    
	/*Uma senha unica para compor a autenticacao*/
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	/*Prefixo  padrao de token*/
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/*Gerando token de autenticacao e adicionado ao cabeçalho e resposta http */
	public void addAuthentication(HttpServletResponse response, String login)  throws java.io.IOException {
		
		/* Montagem do token */
		String JWT = Jwts
				.builder() /* chama o gerador de token */
				.setSubject(login) /* adiciona o user */
				.setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME)) /* tempo de expiracao */
				.signWith(SECRET_KEY).compact(); /* compactacao e algoritmos de geracao de senha */
		
		/* Junta token com o prefixo */
		String token = TOKEN_PREFIX + " " + JWT; 
		
		/* Adiciona no cabeçalho */
		response.addHeader(HEADER_STRING, token);
		
		/* Escreve o token como resposta no corpo http */
		response.getWriter().write("{\"Authorization\": \""+token+"\"}");
		
		
	}
	
	/* Retorna o user validado com token ou caso nao seja valido retorna null */
	public  Authentication getAuthentication(HttpServletRequest request) {
		
		/* Pega o token enviado no cabeçalho http */
		String token = request.getHeader(HEADER_STRING);

	    if (token != null) {
	        try {
	            Claims claims = Jwts.parser()
	                    .setSigningKey(SECRET_KEY)
	                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
	                    .getBody();

	            String user = claims.getSubject();
	            if (user != null) {
	                User usuario = ApplicationContextLoad.getApplicationContext()
	                        .getBean(UserRepository.class)
	                        .findByUserLogin(user);

	                if (usuario != null) {
	                    return new UsernamePasswordAuthenticationToken(
	                            usuario.getLogin(),
	                            usuario.getPassword(),
	                            usuario.getAuthorities()
	                    );
	                }
	            }
	        } catch (SignatureException ex) {
	            throw new SignatureException("Unauthorized");
	        }
	    }

	    return null;
	}
}
