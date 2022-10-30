package com.kunst.gestaoContas.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaldoResponse {
    private Long idConta;
    private BigDecimal saldo;

    public SaldoResponse(Long idConta, BigDecimal valor) {
        this.idConta = idConta;
        this.saldo = valor;
    }
}
