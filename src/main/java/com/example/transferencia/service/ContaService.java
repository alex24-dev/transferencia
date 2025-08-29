package com.example.transferencia.service;

import com.example.transferencia.domain.Conta;
import com.example.transferencia.dto.ContaDTO;
import com.example.transferencia.repository.ContaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    ContaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

//    public ContaDTO cadastrar(ContaDTO contaDTO){
//        Conta conta = modelMapper.map(contaDTO,Conta.class);
//        // Define a data de agendamento como hoje
//        LocalDate hoje = LocalDate.now();
//        conta.setDataAgendamento(hoje);
//
//        // Calcula a diferença em dias entre as datas
//        long dias = ChronoUnit.DAYS.between(hoje, conta.getDataTransferencia());
//
//        // Calcula a taxa com base na tabela
//        BigDecimal taxa = calcularTaxa(conta.getValor(), dias);
//        conta.setTaxa(taxa);
//
//        // Salva a transferência no banco de dados
//        repository.save(conta);
//
//        // Retorna o DTO atualizado
//        return modelMapper.map(conta, ContaDTO.class);
//    }

    public ContaDTO cadastrar(ContaDTO contaDTO) {
        Conta conta = modelMapper.map(contaDTO, Conta.class);

        // Usa a data de agendamento enviada no DTO
        LocalDate dataAgendamento = conta.getDataAgendamento();
        if (dataAgendamento == null) {
            dataAgendamento = LocalDate.now(); // Define como hoje se não for enviada
            conta.setDataAgendamento(dataAgendamento);
        }

        // Calcula a diferença em dias entre as datas
        long dias = ChronoUnit.DAYS.between(dataAgendamento, conta.getDataTransferencia());

        // Calcula a taxa com base na tabela
        BigDecimal taxa = calcularTaxa(conta.getValor(), dias);
        conta.setTaxa(taxa);

        // Salva a transferência no banco de dados
        repository.save(conta);

        // Retorna o DTO atualizado
        return modelMapper.map(conta, ContaDTO.class);
    }

    private BigDecimal calcularTaxa(BigDecimal valor, long dias) {
        if (dias < 0) {
            throw new IllegalArgumentException("A data de transferência não pode ser anterior à data de agendamento.");
        }
        if (dias == 0) {
            return valor.multiply(BigDecimal.valueOf(0.025)); // 2,5%
        } else if (dias >= 1 && dias <= 10) {
            return BigDecimal.ZERO; // 0,0%
        } else if (dias >= 11 && dias <= 20) {
            return valor.multiply(BigDecimal.valueOf(0.082)); // 8,2%
        } else {
            return BigDecimal.ZERO; // Fora do intervalo
        }
    }

//    private BigDecimal calcularTaxa(BigDecimal valor, long dias) {
//        if (dias < 0) {
//            throw new IllegalArgumentException("A data de transferência não pode ser anterior à data de agendamento.");
//        }
//
//        if (dias == 0) {
//            return valor.multiply(BigDecimal.valueOf(0.025)); // 2,5%
//        } else if (dias >= 1 && dias <= 10) {
//            return valor.multiply(BigDecimal.valueOf(0.03)); // 3,0%
//        } else if (dias >= 11 && dias <= 20) {
//            return valor.multiply(BigDecimal.valueOf(0.082)); // 8,2%
//        } else {
//            return valor.multiply(BigDecimal.valueOf(0.05)); // 5,0% para mais de 20 dias
//        }
//    }

    public List<ContaDTO> listar() {
        List<Conta> contas = repository.findAll();
        return contas.stream()
                .map(conta -> modelMapper.map(conta, ContaDTO.class))
                .toList();
    }

}
