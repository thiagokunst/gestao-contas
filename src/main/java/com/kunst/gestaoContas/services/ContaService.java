package com.kunst.gestaoContas.services;

import com.kunst.gestaoContas.dto.*;
import com.kunst.gestaoContas.entities.Conta;
import com.kunst.gestaoContas.entities.Pessoa;
import com.kunst.gestaoContas.entities.Transacao;
import com.kunst.gestaoContas.repositories.ContaRepository;
import com.kunst.gestaoContas.repositories.PessoaRepository;
import com.kunst.gestaoContas.repositories.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;

    public ResponseEntity<Object> criar(ContaRequest contaRequest){
        if (pessoaRepository.findByCpf(contaRequest.getPessoaRequest().getCpf()).isEmpty()){
            return new ResponseEntity<>("Pessoa não encontrada", HttpStatus.BAD_REQUEST);

        }
        Pessoa pessoa = pessoaRepository.findByCpf(contaRequest.getPessoaRequest().getCpf()).get();
        Conta conta = new Conta();
        conta.setPessoa(pessoa);
        conta.setSaldo(contaRequest.getSaldo());
        conta.setLimiteSaqueDiario(contaRequest.getLimiteSaqueDiario());
        conta.setFlagAtivo(true);
        conta.setTipoConta(contaRequest.getTipoConta());

        contaRepository.save(conta);
        ContaResponse contaResponse = new ContaResponse(contaRequest.getPessoaRequest().getCpf(), conta.getIdConta());
        return new ResponseEntity<>(contaResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<String> bloquear(Long id){
        if (contaRepository.findById(id).isEmpty()){
            return new ResponseEntity<>("Conta não encontrada", HttpStatus.BAD_REQUEST);
        }
        Conta conta = contaRepository.findById(id).get();
        conta.setFlagAtivo(false);
        contaRepository.save(conta);
        return new ResponseEntity<>("Conta bloqueada", HttpStatus.OK);
    }

    public ResponseEntity<Object> depositar(TransacaoRequest transacaoRequest, Long id){

        if (contaRepository.findById(id).isEmpty()){
            return new ResponseEntity<>("Conta não encontrada", HttpStatus.NOT_FOUND);
        }

        Conta conta = contaRepository.findById(id).get();

        if(!conta.isFlagAtivo()){
            return new ResponseEntity<>("Essa conta está bloqueada", HttpStatus.BAD_REQUEST);
        }

        conta.setSaldo(conta.getSaldo().add(transacaoRequest.getValor()));
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setIdConta(id);
        transacao.setValor(transacaoRequest.getValor());
        transacaoRepository.save(transacao);

        TransacaoResponse transacaoResponse = new TransacaoResponse(id, transacaoRequest.getValor());
        return new ResponseEntity<>(transacaoResponse, HttpStatus.OK);

    }

    public ResponseEntity<Object> sacar(TransacaoRequest transacaoRequest, Long id) {
        if (contaRepository.findById(id).isEmpty()){
            return new ResponseEntity<>("Conta não encontrada", HttpStatus.NOT_FOUND);
        }
        Conta conta = contaRepository.findById(id).get();

        if(!conta.isFlagAtivo()){
            return new ResponseEntity<>("Esta conta está bloqueada", HttpStatus.BAD_REQUEST);
        }

        if(transacaoRequest.getValor().add(limiteUtilizado(id)).compareTo(conta.getLimiteSaqueDiario()) > 0){
            return new ResponseEntity<>("Limite diário atingido", HttpStatus.BAD_REQUEST);

        }

        if(transacaoRequest.getValor().compareTo(conta.getSaldo()) > 0){
            return new ResponseEntity<>("Saldo insuficiente", HttpStatus.BAD_REQUEST);
        }

        conta.setSaldo(conta.getSaldo().subtract(transacaoRequest.getValor()));
        contaRepository.save(conta);

        Transacao transacao = new Transacao();
        transacao.setIdConta(id);
        transacao.setValor(transacaoRequest.getValor().negate());
        transacaoRepository.save(transacao);


        TransacaoResponse transacaoResponse = new TransacaoResponse(id, transacaoRequest.getValor());
        return new ResponseEntity<>(transacaoResponse, HttpStatus.OK);
    }
    public ResponseEntity<Object> consultarSaldo(Long id) {
        if (contaRepository.findById(id).isEmpty()){
            return new ResponseEntity<>("Conta não encontrada", HttpStatus.NOT_FOUND);
        }
        Conta conta = contaRepository.findById(id).get();
        SaldoResponse saldoResponse = new SaldoResponse(id, conta.getSaldo());
        return new ResponseEntity<>(saldoResponse, HttpStatus.OK);
    }

    public List<Conta> buscarPorCpf(String cpf) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf).get();
        return contaRepository.findAllByPessoa(pessoa);
    }

    public BigDecimal limiteUtilizado(Long id){
        Calendar inicio = new GregorianCalendar();
        inicio.set(Calendar.HOUR_OF_DAY, 0);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);
        inicio.set(Calendar.MILLISECOND, 0);
        
        Calendar fim = new GregorianCalendar();
        fim.set(Calendar.HOUR_OF_DAY, 23);
        fim.set(Calendar.MINUTE, 59);
        fim.set(Calendar.SECOND, 59);
        fim.set(Calendar.MILLISECOND, 999);
        

        var saques = transacaoRepository.findAllSaquesByIdContaAndDataTransacaoBetween(id, inicio.getTime(), fim.getTime());

        return saques.stream().map(i -> i.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add).negate();

    }
}
