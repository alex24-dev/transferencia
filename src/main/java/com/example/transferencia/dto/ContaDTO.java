package com.example.transferencia.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

    @NotBlank
    @NotEmpty(message = "conta de origem não pode ser vazio")
    @Pattern(regexp = "^.{7}$", message = "deve conter exatamente 7 caracteres")
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
