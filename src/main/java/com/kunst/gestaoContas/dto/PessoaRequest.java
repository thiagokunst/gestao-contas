package com.kunst.gestaoContas.dto;

import lombok.Data;

@Data
public class PessoaRequest {
    private Long id;
    private String cpf;

    public PessoaRequest(Long id, String cpf){
        this.id = id;
        this.cpf = cpf;
    }
}
