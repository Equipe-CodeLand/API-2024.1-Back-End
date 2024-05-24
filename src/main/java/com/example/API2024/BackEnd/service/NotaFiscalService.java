package com.example.API2024.BackEnd.service;

import java.io.IOException;
import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Optional;

import com.example.API2024.BackEnd.repository.AtivosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.NotaFiscal;
import com.example.API2024.BackEnd.repository.NotaFiscalRepository;



@Service
public class NotaFiscalService {
	
	@Autowired
	private NotaFiscalRepository repositorio;
	
	@Autowired
	private AtivosRepository ativosRepository;
	
	public void armazenarArquivo(NotaFiscal arquivo) {
		this.repositorio.save(arquivo);
	}
	
	public ResponseEntity<String> receberArquivo(@RequestParam("file") MultipartFile arquivoEnviado, Ativos ativo)
			throws IOException {
		NotaFiscal arquivo = new NotaFiscal();
		arquivo.setBytes(arquivoEnviado.getBytes());
		arquivo.setNome(arquivoEnviado.getOriginalFilename());
		Long tamanho = arquivoEnviado.getSize();
		arquivo.setTamanho(tamanho.toString());
		arquivo.setTipo(arquivoEnviado.getContentType());
		armazenarArquivo(arquivo);
		ativo.setNotaFiscal(arquivo);
		return new ResponseEntity<String>("arquivo recebido", HttpStatus.ACCEPTED);
	}
	
	public List<NotaFiscal> obterArquivos(){
		return this.repositorio.findAll();
	}
	
	public Resource obterArquivoComoRecurso(Long id) {
		NotaFiscal arquivo = repositorio.findById(id).get();
		Resource recurso = new ByteArrayResource(arquivo.getBytes());
		return recurso;
	}
	
	public NotaFiscal obterArquivo(Long id) {
		NotaFiscal arquivo = this.repositorio.findById(id).get();
		return arquivo;
	}
	
	public void excluirArquivo( NotaFiscal arquivo) {
		this.repositorio.delete(arquivo);
	}
	
	public void excluirNotaFiscal(Long idAtivo) {
		Ativos ativo = ativosRepository.findById(idAtivo)
				.orElseThrow(() -> new ProviderNotFoundException("Ativo não encontrado com o ID: " + idAtivo));
		
		NotaFiscal notaFiscal = ativo.getNotaFiscal();
		if (notaFiscal != null) {
			ativo.setNotaFiscal(null);
			repositorio.deleteById(notaFiscal.getId());
		} else {
			throw new ProviderNotFoundException("Nota fiscal não encontrada para o ativo com o ID: " + idAtivo);
		}
	}
}