package com.example.API2024.BackEnd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.API2024.BackEnd.model.Ativos;
import com.example.API2024.BackEnd.dto.AtivosDto;
import com.example.API2024.BackEnd.repository.AtivosRepository;
import com.example.API2024.BackEnd.service.AtivosService;
import com.example.API2024.BackEnd.model.Usuario;
import com.example.API2024.BackEnd.repository.UsuarioRepository;
import com.example.API2024.BackEnd.selecionadores.UsuarioSelecionadorCPF;
import com.example.API2024.BackEnd.service.HistoricoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.API2024.BackEnd.model.NotaFiscal;
import com.example.API2024.BackEnd.repository.NotaFiscalRepository;

import com.example.API2024.BackEnd.service.NotaFiscalService;

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
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioSelecionadorCPF usuarioSelecionadorCPF;
    
    @Autowired
    private NotaFiscalService notaFiscalService;

    @GetMapping("/listar/ativos")
    public List<Ativos> listarAtivos() {
    	ativosService.atualizarAtivoStatus();
         return repositorio.findAll();
    }

    @GetMapping("/listar/ativos/{cpf}")
    public List<Ativos> listarAtivosPorCPF(@PathVariable String cpf) {
        try {
        	List<Usuario> usuarios = usuarioRepository.findAll();
        	Usuario usuario = usuarioSelecionadorCPF.selecionar(usuarios, cpf);
        	ativosService.atualizarAtivoStatus();
            if ("Funcionário".equals(usuario.getCargo().getNome())) {
                return repositorio.findByUsuarioId(usuario.getId());
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @PostMapping(value = "/cadastrar/ativos")
    public void cadastrarAtivos(@RequestPart AtivosDto ativosDto, @RequestParam(value = "file", required = false) MultipartFile arquivoEnviado) throws IOException {
        Ativos ativo = ativosDto.toEntity();
              
        if( arquivoEnviado != null) {
    		NotaFiscal arquivo = new NotaFiscal();
    		arquivo.setBytes(arquivoEnviado.getBytes());
    		arquivo.setNome(arquivoEnviado.getOriginalFilename());
    		Long tamanho = arquivoEnviado.getSize();
    		arquivo.setTamanho(tamanho.toString());
    		arquivo.setTipo(arquivoEnviado.getContentType());
    		notaFiscalService.armazenarArquivo(arquivo);
    		ativo.setNotaFiscal(arquivo);
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
                Ativos ativoSalvo = repositorio.save(ativoAtualizado);

                if(ativoAtualizado.getStatus().getNome_status().equals("Ocupado") && ativo.getUsuario() == ativoAtualizado.getUsuario()) {
                    historicoService.addHistorico(ativoAtualizado);
                }
                return ResponseEntity.ok(ativoSalvo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch(Exception e) {
            System.out.println("Erro ao atualizar ativo: " + e.getMessage());
            return null;
        }
    }

    @DeleteMapping("/delete/ativos/{id}")
    public void deleteAtivos(@PathVariable Long id) {
        ativosService.delete(id);
    }
}
