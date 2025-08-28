package com.example.transferencia.service;

import com.example.transferencia.domain.Conta;
import com.example.transferencia.dto.ContaDTO;
import com.example.transferencia.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {

    @Autowired
    ContaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ContaDTO cadastrar(ContaDTO contaDTO){
        Conta conta = modelMapper.map(contaDTO,Conta.class);
        repository.save(conta);
        return modelMapper.map(conta,ContaDTO.class);
    }
}
