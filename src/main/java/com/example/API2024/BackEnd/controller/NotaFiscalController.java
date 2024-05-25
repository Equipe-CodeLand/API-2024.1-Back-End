package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.NotaFiscal;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.repository.NotaFiscalRepository;
import com.example.API2024.BackEnd.service.NotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NotaFiscalController {
	@Autowired
	private NotaFiscalService notaFiscalService;
	
	@Autowired
	private NotaFiscalRepository notaFiscalRepository;
	
	@Autowired
	private AtivosRepository ativosRepository;
	
	@PostMapping("/cadastrar/nota-fiscal/{idAtivo}")
	public ResponseEntity<String> cadastrarNotaFiscal(@PathVariable Long idAtivo, @RequestParam("file") MultipartFile arquivoEnviado)
			throws IOException {
		NotaFiscal arquivo = new NotaFiscal();
		arquivo.setBytes(arquivoEnviado.getBytes());
		arquivo.setNome(arquivoEnviado.getOriginalFilename());
		Long tamanho = arquivoEnviado.getSize();
		arquivo.setTamanho(tamanho.toString());
		arquivo.setTipo(arquivoEnviado.getContentType());
		notaFiscalService.armazenarArquivo(arquivo);
		
		// Obtém o ID da nota fiscal criada
		Long idNotaFiscal = arquivo.getId();
		
		// Encontra a nota fiscal usando o ID
		NotaFiscal notaFiscal = notaFiscalRepository.findById(idNotaFiscal).orElse(null);
		
		// Encontra o ativo correspondente usando o ID do ativo fornecido
		Ativos ativo = ativosRepository.findById(idAtivo).orElse(null);
		
		// Associa a nota fiscal ao ativo
		if (notaFiscal != null && ativo != null) {
			ativo.setNotaFiscal(notaFiscal);
			ativosRepository.save(ativo); // Salva o ativo atualizado com a nota fiscal
			return ResponseEntity.accepted().body("Nota fiscal associada ao ativo com sucesso.");
		} else {
			return ResponseEntity.badRequest().body("Falha ao associar a nota fiscal ao ativo.");
		}
	}
	
	@GetMapping("/ativos/nota-fiscal/{id}")
	public ResponseEntity<Resource> buscarNotaFiscal(@PathVariable long id) {
		Resource recurso = notaFiscalService.obterArquivoComoRecurso(id);
		if (recurso == null) {
			ResponseEntity<Resource> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return resposta;
		} else {
			NotaFiscal arquivo = notaFiscalService.obterArquivo(id);
			MediaType tipoArquivo = MediaType.parseMediaType(arquivo.getTipo());
			ResponseEntity<Resource> resposta = ResponseEntity.ok().contentType(tipoArquivo).body(recurso);
			return resposta;
		}
	}
	
	@DeleteMapping("/ativos/excluir/nota-fiscal/{idAtivo}")
	public void excluirNotaFiscal(@PathVariable Long idAtivo) {
		notaFiscalService.excluirNotaFiscal(idAtivo);
	}
}
