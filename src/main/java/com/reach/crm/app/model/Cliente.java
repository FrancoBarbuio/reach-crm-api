package com.reach.crm.app.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombreEmpresa;

    @Column(length = 100)
    private String sector;

    @Column(nullable = false, unique = true, length = 100)
    private String emailContacto;

    @Column(length = 20)
    private String telefono;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Oportunidad> oportunidades = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombreEmpresa, String sector, String emailContacto, String telefono) {
        this.nombreEmpresa = nombreEmpresa;
        this.sector = sector;
        this.emailContacto = emailContacto;
        this.telefono = telefono;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String nombreEmpresa) { this.nombreEmpresa = nombreEmpresa; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getEmailContacto() { return emailContacto; }
    public void setEmailContacto(String emailContacto) { this.emailContacto = emailContacto; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Oportunidad> getOportunidades() { return oportunidades; }
    public void setOportunidades(List<Oportunidad> oportunidades) { this.oportunidades = oportunidades; }
}