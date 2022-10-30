package com.kunst.gestaoContas.repositories;

import com.kunst.gestaoContas.entities.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

//    @Query("SELECT * from dbo.transacoes WHERE id_conta = :id")
    List<Transacao> findAllByIdConta(Long id);
    List<Transacao> findAllByIdContaAndDataTransacaoBetween(Long idConta, Date inicio, Date fim);

    @Query(value = "SELECT * from dbo.transacoes WHERE valor < 0 AND id_conta = ?1 AND data_transacao >= ?2 AND data_transacao <= ?3", nativeQuery = true)
    List<Transacao>findAllSaquesByIdContaAndDataTransacaoBetween(Long idConta, Date inicio, Date fim);

    @Query(value = "SELECT * from dbo.transacoes WHERE valor > 0 AND id_conta = ?1 AND data_transacao >= ?2 AND data_transacao <= ?3", nativeQuery = true)
    List<Transacao> findAllDepositosByIdContaAndDataTransacaoBetween(Long id, Date inicio, Date fim);

    @Query(value = "SELECT * from dbo.transacoes WHERE valor < 0 AND id_conta = ?1", nativeQuery = true)
    List<Transacao> findAllSaques(Long id);

    @Query(value = "SELECT * from dbo.transacoes WHERE valor > 0 AND id_conta = ?1", nativeQuery = true)
    List<Transacao> findAllDepositos(Long id);
}
