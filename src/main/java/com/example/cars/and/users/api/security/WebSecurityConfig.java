package com.example.cars.and.users.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.cars.and.users.api.service.UserDetailsServiceImpl;

/*Mapeia a URL, enderecos, autoriza ou bloqueia os acessos*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	/* Configura as solicitacoes de acesso por http */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* Ativando a validacao de user que nao estao validados por token */
		http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

				/* Ativando a permissoes as URLs */
				.disable().authorizeRequests()
				.antMatchers("/h2-console/**").permitAll() // Permitir acesso ao console H2
				.antMatchers("/api/signin").permitAll() // Permitir acesso à rota de signin
				.antMatchers("/api/users").permitAll() // Permitir acesso à rota de listagem de usuários
				.antMatchers("/api/users/{id}").permitAll() // Permitir acesso à rota de busca de usuário pelo ID
				.antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permitir acesso ao Swagger UI
				
				.antMatchers("/index").permitAll()
				
				.antMatchers("/api/me").authenticated()  // Exige autenticação para a rota /api/me
		        .antMatchers("/api/cars").authenticated()  // Exige autenticação para a rota /api/cars
		        .antMatchers("/api/cars/{id}").authenticated() // Exige autenticação para a rota /api/cars/{id}
				.anyRequest().permitAll()
				.and()
				.logout().logoutSuccessUrl("/index")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and().headers().frameOptions().sameOrigin()

				// Filtar requisicoes de login para autenticacao
				.and()
				.addFilterBefore(new JwtLoginFilter("/api/signin", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)

				// Filtar demais requisicoes para verificar a presenca do token jwt no header
				// http
				.addFilterBefore(new JwtApiAutenticacaoFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*service que ira consultar o user no banco de dados*/
		auth.userDetailsService(userDetailsServiceImpl)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		super.configure(auth);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
    }

	
}
