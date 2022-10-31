package com.kunst.gestaoContas.dto;

import lombok.Data;

@Data
public class ContaResponse {
    private String cpf;
    private Long idConta;

    public ContaResponse(String cpf, Long idConta) {
        this.cpf = cpf;
        this.idConta = idConta;
    }
}
