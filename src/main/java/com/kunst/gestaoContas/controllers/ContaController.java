package com.kunst.gestaoContas.controllers;

import com.kunst.gestaoContas.dto.*;
import com.kunst.gestaoContas.entities.Conta;
import com.kunst.gestaoContas.services.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping(value = "/conta")
@Tag(name="Conta")
public class ContaController {

    @Autowired

    private ContaService contaService;

    public List<Conta> findAll(){

        return null;
    }

    @PostMapping
    @Operation(summary = "Criar uma nova conta")
    public ResponseEntity<Object> criar(@RequestBody ContaRequest contaRequest){
        return contaService.criar(contaRequest);
    }
    @PatchMapping("/{id}")
    @Operation(summary = "Efetuar o bloqueio de uma conta")
    public ResponseEntity<String> bloquear(@PathVariable Long id){

        return contaService.bloquear(id) ;
    }

    @PostMapping("/deposito/{id}")
    @Operation(summary = "Efetuar um depósito em uma conta")
    public ResponseEntity<Object> depositar(@RequestBody TransacaoRequest transacaoRequest, @PathVariable Long id){
        return contaService.depositar(transacaoRequest, id);
    }

    @PostMapping("/saque/{id}")
    @Operation(summary = "Efetuar um saque em uma conta")
    public ResponseEntity<Object> sacar(@RequestBody TransacaoRequest transacaoRequest, @PathVariable Long id ) {
        return contaService.sacar(transacaoRequest, id);
    }

    @GetMapping("/saldo/{id}")
    @Operation(summary = "Consultar o saldo de uma conta")
    public ResponseEntity<Object> consultarSaldo(@PathVariable Long id){
        return contaService.consultarSaldo(id);
    }

    @GetMapping("/buscar-por-cpf")
    @Operation(summary = "Buscar todas as contas cadastradas em determinado CPF")
    public List<Conta> buscarPorCpf(@RequestBody @NotNull PessoaRequest pessoaRequest){
        return contaService.buscarPorCpf(pessoaRequest.getCpf());
    }
}
