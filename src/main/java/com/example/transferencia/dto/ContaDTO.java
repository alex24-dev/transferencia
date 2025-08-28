package com.example.transferencia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaDTO {

    @NotBlank
    @NotEmpty(message = "nome não pode ser vazio")
    private String contaOrigem;

    @NotBlank
    private String contaDestino;

    @NotNull
    @Positive(message = "preço não pode ser negativo")
    private Integer valor;
}
