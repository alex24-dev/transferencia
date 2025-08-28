package com.example.transferencia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    @NotBlank
    @NotEmpty(message = "conta de origem não pode ser vazio")
    private String contaOrigem;

    @NotBlank
    @NotEmpty(message = "conta de destino não pode ser vazio")
    private String contaDestino;

    @NotNull
    @Positive(message = "valor não pode ser negativo")
    private BigDecimal valor;


    private LocalDate dataTransferencia;
    private LocalDate dataAgendamento;

}
