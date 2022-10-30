package com.kunst.gestaoContas.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idTransacao;

    @Column(name = "id_conta")
    private Long idConta;

    @Column(name = "valor")
    private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_transacao")
    private Date dataTransacao;

    @PrePersist
    private void onCreate(){
        dataTransacao = new Date();
    }

}
