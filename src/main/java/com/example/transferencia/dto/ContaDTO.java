package com.example.transferencia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
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

    private BigDecimal calcularTaxa(long dias) {
        if (dias == 0) {
            return this.valor.multiply(BigDecimal.valueOf(0.025)); // 2,5%
        } else if (dias >= 1 && dias <= 10) {
            return BigDecimal.ZERO; // 0%
        } else if (dias >= 11 && dias <= 20) {
            return this.valor.multiply(BigDecimal.valueOf(0.082)); // 8,2%
        } else {
            throw new IllegalArgumentException("Não há taxa definida para mais de 20 dias.");
        }
    }
}
