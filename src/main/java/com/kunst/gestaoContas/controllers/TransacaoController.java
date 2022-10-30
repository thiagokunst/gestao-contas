package com.kunst.gestaoContas.controllers;

import com.kunst.gestaoContas.entities.Transacao;
import com.kunst.gestaoContas.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/transacao")
@Tag(name="Transação")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    public List<Transacao> findAll(){

        return null;
    }

    @GetMapping("/extrato/{id}")
    @Operation(summary = "Consultar todo o extrato de uma determinada conta")
    public List<Transacao> consultarExtrato(@PathVariable Long id){
        return transacaoService.consultarExtrato(id);
    }

    @GetMapping("extrato-periodo/{id}")
    @Operation(summary = "Consultar o extrato de uma determinada conta em um período")
    public List<Transacao> consultarExtratoPorPeriodo(@PathVariable Long id,
                                                      @RequestParam(value = "inicio") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date inicio,
                                                      @RequestParam(value = "fim") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date fim){
        return transacaoService.consultarExtratoPorPeriodo(id, inicio, fim);
    }

    @GetMapping("saques/{id}")
    @Operation(summary = "Consultar todos os saques de uma determinada conta")
    public List<Transacao> consultarSaques(@PathVariable Long id){
        return transacaoService.consultarSaques(id);
    }

    @GetMapping("depositos/{id}")
    @Operation(summary = "Consultar todos os depósitos de uma determinada conta")
    public List<Transacao> consultarDepositos(@PathVariable Long id){
        return transacaoService.consultarDepositos(id);
    }

    @GetMapping("saques-periodo/{id}")
    @Operation(summary = "Consultar os saque de uma determinada conta em um período")
    public List<Transacao> consultarSaquesPorPeriodo(@PathVariable Long id,
                                                     @RequestParam(value = "inicio") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date inicio,
                                                     @RequestParam(value = "fim") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date fim){
        return transacaoService.consultarSaquesPorPeriodo(id, inicio, fim);
    }

    @GetMapping("depositos-periodo/{id}")
    @Operation(summary = "Consultar os depósitos de uma determinada conta em um período")
    public List<Transacao> consultarDepositosPorPeriodo(@PathVariable Long id,
                                                        @RequestParam(value = "inicio") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date inicio,
                                                        @RequestParam(value = "fim") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date fim){
        return transacaoService.consultarDepositosPorPeriodo(id, inicio, fim);
    }

}
