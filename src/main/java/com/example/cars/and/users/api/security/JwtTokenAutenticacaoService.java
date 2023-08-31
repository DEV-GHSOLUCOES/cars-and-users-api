package com.example.cars.and.users.api.security;
import java.io.IOException;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.cars.and.users.api.ApplicationContextLoad;
import com.example.cars.and.users.api.model.User;
import com.example.cars.and.users.api.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
@Component
public class JwtTokenAutenticacaoService {
	
	
	/*Tempo de validade do token */
	private static final long EXPIRATION_TIME = 300000 ;
    
	/*Uma senha unica para compor a autenticacao*/
	private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	
	/*Prefixo  padrao de token*/
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/* Gerando token de autenticacao e adicionado ao cabeçalho e resposta http */
	public void addAuthentication(HttpServletResponse response, String login) throws java.io.IOException {

		/* Montagem do token */
		String JWT = Jwts.builder() /* chama o gerador de token */
				.setSubject(login) /* adiciona o user */
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) /* tempo de expiracao */
				.signWith(SECRET_KEY).compact(); /* compactacao e algoritmos de geracao de senha */

		/* Junta token com o prefixo */
		String token = TOKEN_PREFIX + " " + JWT;

		/* Adiciona no cabeçalho */
		response.addHeader(HEADER_STRING, token);

		ApplicationContextLoad.getApplicationContext().getBean(UserRepository.class).updateTokenUser(JWT, login);

		/* liberando resposta para portas diferentes que usam a API */
		liberacaoCors(response);

		/* Escreve o token como resposta no corpo http */
		response.getWriter().write("{\"Authorization\": \"" + token + "\"}");

	}
	
	

	/* Retorna o user validado com token ou caso nao seja valido retorna null */
	public  Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		/* Pega o token enviado no cabeçalho http */
		String token = request.getHeader(HEADER_STRING);

	    if (token != null) {
	        try {
	        	
	        	String tokenClean = token.replace(TOKEN_PREFIX, "").trim();
	        	
	            Claims claims = Jwts.parser()
	                    .setSigningKey(SECRET_KEY)
	                    .parseClaimsJws(tokenClean)
	                    .getBody();

	            String login = claims.getSubject();
	            if (login != null) {
	                User user = ApplicationContextLoad.getApplicationContext()
	                        .getBean(UserRepository.class).findByUserLogin(login);

	                if (user != null) {
	                	
	                	
	                	if (tokenClean.equalsIgnoreCase(user.getToken())) {
							
	                		return new UsernamePasswordAuthenticationToken(
	                				user.getLogin(),
	                				user.getPassword(),
	                				user.getAuthorities());
						}
	                }
	            }
	        } catch (SignatureException ex) {
	        	try {
					response.getOutputStream().println("Unauthorized");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
	        }
	        catch (ExpiredJwtException ex) {
				try {
					response.getOutputStream().println("Unauthorized - invalid session");
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
	    }

	    liberacaoCors(response);
	    return null;
	}

	private void liberacaoCors(HttpServletResponse response) {
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
}
