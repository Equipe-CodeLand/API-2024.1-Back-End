package com.example.API2024.BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.adaptadores.UserDetailsImpl;
import com.example.API2024.BackEnd.dto.AcessoDto;
import com.example.API2024.BackEnd.dto.AutenticacaoDto;
import com.example.API2024.BackEnd.jwt.JwtGenerator;

@Service
public class AutenticacaoService {

		@Autowired
		private AuthenticationManager gerenciadorAutenticacao;
		
		@Autowired
		private UserDetailsServiceImpl servico;
		
		@Autowired
		private JwtGenerator jwtGenerator;
		
		public ResponseEntity<?> login(AutenticacaoDto autenticacaoDto) {
			try {
				UsernamePasswordAuthenticationToken userAuth = 
						new UsernamePasswordAuthenticationToken(autenticacaoDto.getCpf(), autenticacaoDto.getSenha());
				
				Authentication authentication = gerenciadorAutenticacao.authenticate(userAuth);
				
				UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
				
				String token = jwtGenerator.generateTokenFromUserDetailsImpl(userAuthenticate);
				
				AcessoDto accessDto = new AcessoDto(token);
				
				return ResponseEntity.ok(accessDto);
				
				}catch(BadCredentialsException e) {
					return new ResponseEntity<>("CPF ou senha incorretos", HttpStatus.UNAUTHORIZED);
				} catch(DisabledException e) {
					return new ResponseEntity<>("Usuário inativo", HttpStatus.UNAUTHORIZED);
				}
			}
}
