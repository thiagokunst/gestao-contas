package com.kunst.gestaoContas.dto;

import lombok.Data;

@Data
public class ContaResponse {
    private Long idPessoa;
    private Long idConta;

    public ContaResponse(Long idPessoa, Long idConta) {
        this.idPessoa = idPessoa;
        this.idConta = idConta;
    }
}
