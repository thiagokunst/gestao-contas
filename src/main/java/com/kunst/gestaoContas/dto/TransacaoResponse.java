package com.kunst.gestaoContas.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoResponse {

    private Long idConta;
    private BigDecimal valor;

    public TransacaoResponse(Long idConta, BigDecimal valor){
        this.idConta = idConta;
        this.valor = valor;
    }
}
