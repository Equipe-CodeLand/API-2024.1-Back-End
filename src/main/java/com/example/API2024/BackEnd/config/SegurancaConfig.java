package com.example.API2024.BackEnd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.API2024.BackEnd.jwt.AuthEntryPoint;
import com.example.API2024.BackEnd.jwt.AuthFilterToken;


@Configuration
@EnableMethodSecurity
public class SegurancaConfig{
	
	@Autowired
	private AuthEntryPoint unauthorizedHandler;
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    private static final String[] rotasPublicas = {"/login", "/usuario/cadastrar", "/credencial/{cpf}/senha", "/credencial/{cpf}/senha/{codigoVerificacao}", "/credencial/{cpf}/verificar-senha", "/ativos/nota-fiscal/{id}", "/cadastrar/nota-fiscal", "/ativos/excluir/nota-fiscal/{idAtivo}"};
    
	@Bean
	public AuthFilterToken authFilterToken() {
		return new AuthFilterToken();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.cors(Customizer.withDefaults());
		http.csrf(csrf -> csrf.disable())
			.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth.requestMatchers(rotasPublicas).permitAll()
							.anyRequest().authenticated());
		
		http.addFilterBefore(authFilterToken(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}	


	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}


   
//    public CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource fonte = new UrlBasedCorsConfigurationSource();
//        fonte.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return fonte;
//    }
}
