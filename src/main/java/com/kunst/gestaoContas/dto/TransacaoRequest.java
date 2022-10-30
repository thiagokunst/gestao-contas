package com.kunst.gestaoContas.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransacaoRequest {

    private BigDecimal valor;

}
