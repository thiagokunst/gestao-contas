package com.kunst.gestaoContas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;


@Data
public class ContaRequest {

    private PessoaRequest pessoaRequest;
    private BigDecimal saldo;
    private BigDecimal limiteSaqueDiario;
    private boolean flagAtivo;
    private Integer tipoConta;

}
