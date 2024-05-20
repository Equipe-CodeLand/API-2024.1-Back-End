package com.example.API2024.BackEnd.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.API2024.BackEnd.dto.AtivosDto;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.model.NotaFiscal;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.repository.NotaFiscalRepository;
import com.example.API2024.BackEnd.service.AtivosService;
import com.example.API2024.BackEnd.service.HistoricoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AtivosController {

    @Autowired
    public AtivosRepository repositorio;
    @Autowired
    public NotaFiscalRepository notaFiscalRepository;
    
    @Autowired
    public AtivosService ativoService;

    @Autowired
    private HistoricoService historicoService;
    
    @Autowired
    public AtivosService ativosService;

    @GetMapping("/listar/ativos")
    public List<Ativos> listarAtivos() {
        ativosService.atualizarAtivoStatus();
        return repositorio.findAll();
    }
    
    @GetMapping("/ativos/nota-fiscal/{id}")
    public ResponseEntity<Resource> buscarNotaFiscal(@PathVariable("id") Long id) {
    	NotaFiscal notaFiscal = this.notaFiscalRepository.findById(id).get();
    	if(notaFiscal.getBytes() != null) {
    		Resource recurso = new ByteArrayResource(notaFiscal.getBytes());
    		MediaType tipoArquivo = MediaType.parseMediaType(notaFiscal.getTipo());
    		ResponseEntity<Resource> resposta = ResponseEntity.ok().contentType(tipoArquivo).body(recurso);    		
    		return resposta;
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

    @PostMapping(value = "/cadastrar/ativos")
    public void cadastrarAtivos(@RequestPart AtivosDto ativosDto, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Ativos ativo = ativosDto.toEntity();
        
        NotaFiscal notaFiscal = new NotaFiscal();
        
        if(ativosDto.getCodigoNotaFiscal() != null) {
        	notaFiscal.setCodigo(ativosDto.getCodigoNotaFiscal());
        	ativo.setNotaFiscal(notaFiscal);
        }        
        if( file != null) {
        	notaFiscal.setBytes(file.getBytes());
        	notaFiscal.setNome(file.getOriginalFilename());
        	Long tamanho = file.getSize();
        	notaFiscal.setTamanho(tamanho.toString());
        	notaFiscal.setTipo(file.getContentType());
        	ativo.setNotaFiscal(notaFiscal);
        }
        
        Ativos savedAtivo = repositorio.save(ativo);

        if ("ocupado".equalsIgnoreCase(savedAtivo.getStatus().getNome_status())) {
            historicoService.addHistorico(savedAtivo);
        }
    }

    @PutMapping("/atualizar/ativos/{id}")
    public ResponseEntity<Ativos> atualizarAtivos(@RequestBody AtivosDto ativosDto, @PathVariable Long id) {
        try {
        	Ativos ativo = repositorio.findById(id).orElse(null);
            if (ativo != null) {
                Ativos ativoAtualizado = ativosService.update(id,ativosDto);
                Ativos ativoSalvo =repositorio.save(ativoAtualizado);

                if(ativoAtualizado.getStatus().getNome_status().equals("Ocupado") && ativo.getUsuario() == ativoAtualizado.getUsuario()) {
                    historicoService.addHistorico(ativoAtualizado);
                }
                return ResponseEntity.ok(ativoSalvo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e) {
        	System.out.println("Erro ao atualizar a manutenção: " + e.getMessage());
			return null;
        }
    }

    @DeleteMapping("/delete/ativos/{id}")
    public void deleteAtivos(@PathVariable Long id) {
        ativosService.delete(id);
    }
}
