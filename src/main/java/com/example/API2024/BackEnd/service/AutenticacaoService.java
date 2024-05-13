package com.example.API2024.BackEnd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
		
		public AcessoDto login(AutenticacaoDto autenticacaoDto) {
		    try {
		        UsernamePasswordAuthenticationToken userAuth = 
		                new UsernamePasswordAuthenticationToken(autenticacaoDto.getCpf(), autenticacaoDto.getSenha());
		        
		        Authentication authentication = gerenciadorAutenticacao.authenticate(userAuth);
		        
		        UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
		        
		        String token = jwtGenerator.generateTokenFromUserDetailsImpl(userAuthenticate);
		        
		        AcessoDto accessDto = new AcessoDto(token);
		        
		        return accessDto;
		        
		    }catch(BadCredentialsException e) {
		        //TODO LOGIN OU SENHA INVALIDO
		    }
		    return new AcessoDto("Acesso negado");
		}
}
