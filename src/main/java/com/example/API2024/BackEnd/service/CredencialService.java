package com.example.API2024.BackEnd.service;

import com.example.API2024.BackEnd.adaptadores.EmailServiceImpl;
import com.example.API2024.BackEnd.interfaces.EmailInterface;
import com.example.API2024.BackEnd.model.Credencial;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.CredencialRepository;
import com.example.API2024.BackEnd.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class CredencialService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CredencialRepository credencialRepository;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private Map<String, String> codigoVerificacaoMap = new HashMap<>();
	
	public Credencial criarCredencial(String cpf, String senha) throws Exception {
		if (credencialRepository.existsByCpf(cpf)) {
			throw new Exception("CPF em uso");
		}
		Credencial credencial = new Credencial();
		credencial.setCpf(cpf);
		credencial.setSenha(passwordEncoder.encode(senha));
		
		credencialRepository.save(credencial);
		
		return credencial;
	}
	
	public boolean verificarSenhaIgual(String cpf, String senha) throws Exception {
		Credencial credencial = credencialRepository.findByCpf(cpf);
		if (credencial != null) {
			return passwordEncoder.matches(senha, credencial.getSenha());
		} else {
			throw new Exception("Credencial não encontrada para o CPF fornecido");
		}
	}
	
	public void enviarCodigoVerificacao(String cpf, String email) throws Exception {
		String codigoVerificacao = gerarCodigoVerificacao();
		String emailUsuario = getEmailDoUsuarioLogado(cpf, email);
		emailService.sendMail(emailUsuario, "Senha Alterada - Código de Verificação", "Seu código de verificação: " + codigoVerificacao);
		codigoVerificacaoMap.put(emailUsuario, codigoVerificacao);
	}
	
	public void alterarSenha(String cpf, String novaSenha, String email, String codigo) throws Exception {
		String emailUsuario = getEmailDoUsuarioLogado(cpf, email);
		String codigoArmazenado = codigoVerificacaoMap.get(emailUsuario);
		if (codigoArmazenado != null && codigoArmazenado.equals(codigo)) {
			Credencial credencial = credencialRepository.findByCpf(cpf);
			if (credencial != null) {
				credencial.setSenha(passwordEncoder.encode(novaSenha));
				credencialRepository.save(credencial);
			} else {
				throw new Exception("Credencial não encontrada para o CPF fornecido");
			}
		} else {
			throw new Exception("Código de verificação incorreto");
		}
	}
	
	private String getEmailDoUsuarioLogado(String cpf, String email) throws Exception {
		Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);
		if (usuario == null) {
			return email;
		}
		return usuario.getEmail();
	}
	
	private String gerarCodigoVerificacao() {
		// Geração de um código alfanumérico aleatório de 6 dígitos
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder codigo = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			codigo.append(caracteres.charAt(random.nextInt(caracteres.length())));
		}
		return codigo.toString();
	}
	
	public boolean verificarCodigo(String email, String codigo) {
		String codigoArmazenado = codigoVerificacaoMap.get(email);
		return codigoArmazenado != null && codigoArmazenado.equals(codigo);
	}
}
