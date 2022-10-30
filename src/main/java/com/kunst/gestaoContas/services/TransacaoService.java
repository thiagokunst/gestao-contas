package com.kunst.gestaoContas.services;

import com.kunst.gestaoContas.entities.Transacao;
import com.kunst.gestaoContas.repositories.ContaRepository;
import com.kunst.gestaoContas.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ContaRepository contaRepository;

//    public ResponseEntity<DepositoResponse> credito(DepositoRequest depositoRequest){
//
//
//        DepositoResponse depositoResponse = new DepositoResponse(depositoRequest.getIdConta(), depositoRequest.getValor());
//        return new ResponseEntity<DepositoResponse>(depositoResponse, HttpStatus.OK);
//    }

    public List<Transacao> consultarExtrato(Long id){
        return transacaoRepository.findAllByIdConta(id);
    }

    public List<Transacao> consultarExtratoPorPeriodo(Long id, Date inicio, Date fim){
        return transacaoRepository.findAllByIdContaAndDataTransacaoBetween(id, inicio, fim);
    }

    public List<Transacao> consultarSaquesPorPeriodo(Long id, Date inicio, Date fim) {
        return transacaoRepository.findAllSaquesByIdContaAndDataTransacaoBetween(id, inicio, fim);
    }

    public List<Transacao> consultarDepositosPorPeriodo(Long id, Date inicio, Date fim) {
        return transacaoRepository.findAllDepositosByIdContaAndDataTransacaoBetween(id, inicio, fim);
    }

    public List<Transacao> consultarSaques(Long id) {
        return transacaoRepository.findAllSaques(id);
    }

    public List<Transacao> consultarDepositos(Long id) {
        return transacaoRepository.findAllDepositos(id);
    }
}
