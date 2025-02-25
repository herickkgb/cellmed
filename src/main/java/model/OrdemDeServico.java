package model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import enums.StatusOrdem;

@Entity
@Table(name = "ordens_servico")
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @ManyToOne
    @JoinColumn(name = "smartphone_id", nullable = false) 
    private Smartphone smartphone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusOrdem status;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    public OrdemDeServico() {
        this.status = StatusOrdem.SOLICITADO;
        this.dataCriacao = LocalDateTime.now();
    }

    public OrdemDeServico(Smartphone smartphone) {
        this.smartphone = smartphone;
        this.status = StatusOrdem.SOLICITADO;
        this.dataCriacao = LocalDateTime.now();
    }

    public void atualizarStatus(StatusOrdem novoStatus) {
        this.status = novoStatus;
        if (novoStatus == StatusOrdem.FINALIZADO) {
            this.dataConclusao = LocalDateTime.now();
        }
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Smartphone getSmartphone() {
        return smartphone;
    }

    public StatusOrdem getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }
}
