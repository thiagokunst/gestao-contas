package com.kunst.gestaoContas.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExtratoResponse {

    private Long id;
    private List extrato;

    public ExtratoResponse(Long id, List extrato){
        this.id = id;
        this.extrato = extrato;
    }

}
