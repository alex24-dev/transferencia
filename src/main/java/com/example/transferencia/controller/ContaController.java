package com.example.transferencia.controller;

import com.example.transferencia.dto.ContaDTO;
import com.example.transferencia.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/conta")
public class ContaController {

    @Autowired
    ContaService service;

    @PostMapping
    public ResponseEntity<ContaDTO> cadastrar(@RequestBody @Valid ContaDTO dto, UriComponentsBuilder uri){
        ContaDTO contaDTO = service.cadastrar(dto);
        URI endereco = uri.path("/conta").buildAndExpand().toUri();
        return ResponseEntity.created(endereco).body(contaDTO);
    }
}
