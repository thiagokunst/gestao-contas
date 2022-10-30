package com.kunst.gestaoContas.entities;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idConta;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Column(name = "saldo")
    private BigDecimal saldo;

    @Column(name = "limite_saque_diario")
    private BigDecimal limiteSaqueDiario;

    @Column(name = "flag_ativo")
    private boolean flagAtivo;

    @Column(name = "tipo_conta")
    private Integer tipoConta;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_criacao")
    private Date dataCriacao;

    @PrePersist
    private void onCreate(){
        dataCriacao = new Date();
    }

}
