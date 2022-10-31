package com.kunst.gestaoContas.dto;

import lombok.Data;

@Data
public class PessoaRequest {
    private String cpf;

    public PessoaRequest(Long id, String cpf){
        this.cpf = cpf;
    }
}
