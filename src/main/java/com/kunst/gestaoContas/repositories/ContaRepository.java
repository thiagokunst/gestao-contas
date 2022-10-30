package com.kunst.gestaoContas.repositories;

import com.kunst.gestaoContas.entities.Conta;
import com.kunst.gestaoContas.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository <Conta, Long> {

    Optional<Conta> findByPessoa(Pessoa pessoa);
    List<Conta> findAllByPessoa(Pessoa pessoa);

}
