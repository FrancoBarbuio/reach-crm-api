package com.reach.crm.app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "oportunidades")
public class Oportunidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal montoEsperado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EtapaVenta etapa;

    @Column(nullable = false)
    private LocalDate fechaCierreEstimada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Oportunidad() {
    }

    public Oportunidad(String titulo, BigDecimal montoEsperado, EtapaVenta etapa, LocalDate fechaCierreEstimada, Cliente cliente) {
        this.titulo = titulo;
        this.montoEsperado = montoEsperado;
        this.etapa = etapa;
        this.fechaCierreEstimada = fechaCierreEstimada;
        this.cliente = cliente;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public BigDecimal getMontoEsperado() { return montoEsperado; }
    public void setMontoEsperado(BigDecimal montoEsperado) { this.montoEsperado = montoEsperado; }

    public EtapaVenta getEtapa() { return etapa; }
    public void setEtapa(EtapaVenta etapa) { this.etapa = etapa; }

    public LocalDate getFechaCierreEstimada() { return fechaCierreEstimada; }
    public void setFechaCierreEstimada(LocalDate fechaCierreEstimada) { this.fechaCierreEstimada = fechaCierreEstimada; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}