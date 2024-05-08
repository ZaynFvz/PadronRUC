package com.piru.padronrucloader.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "padron_ruc")
public class PadronRUC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created;

    @Column(name = "creator", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String creator;

    @Column(name = "active", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean active;

    @Column(name = "ruc", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String ruc;

    @Column(name = "nombre_razon_social", nullable = false, columnDefinition = "VARCHAR DEFAULT '-'")
    private String nombreRazonSocial;

    @Column(name = "estado_contribuyente", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String estadoContribuyente;

    @Column(name = "condicion_domicilio", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String condicionDomicilio;

    @Column(name = "ubigeo", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String ubigeo;

    @Column(name = "tipo_via", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '-'")
    private String tipoVia;

    @Column(name = "nombre_via", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String nombreVia;

    @Column(name = "codigo_zona", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String codigoZona;

    @Column(name = "tipo_zona", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String tipoZona;

    @Column(name = "numero", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String numero;

    @Column(name = "interior", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String interior;

    @Column(name = "lote", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String lote;

    @Column(name = "departamento", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String departamento;

    @Column(name = "manzana", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String manzana;

    @Column(name = "kilometro", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '-'")
    private String kilometro;

    // Constructor
    public PadronRUC() {
        this.created = LocalDateTime.now();
        this.creator = "-";
        this.active = true;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getEstadoContribuyente() {
        return estadoContribuyente;
    }

    public void setEstadoContribuyente(String estadoContribuyente) {
        this.estadoContribuyente = estadoContribuyente;
    }

    public String getCondicionDomicilio() {
        return condicionDomicilio;
    }
    
    public void setCondicionDomicilio(String condicionDomicilio) {
        this.condicionDomicilio = condicionDomicilio;
    }
    
    public String getUbigeo() {
        return ubigeo;
    }
    
    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }
    
    public String getTipoVia() {
        return tipoVia;
    }
    
    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }
    
    public String getNombreVia() {
        return nombreVia;
    }
    
    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }
    
    public String getCodigoZona() {
        return codigoZona;
    }
    
    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
    }
    
    public String getTipoZona() {
        return tipoZona;
    }
    
    public void setTipoZona(String tipoZona) {
        this.tipoZona = tipoZona;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getInterior() {
        return interior;
    }
    
    public void setInterior(String interior) {
        this.interior = interior;
    }
    
    public String getLote() {
        return lote;
    }
    
    public void setLote(String lote) {
        this.lote = lote;
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
    
    public String getManzana() {
        return manzana;
    }
    
    public void setManzana(String manzana) {
        this.manzana = manzana;
    }
    
    public String getKilometro() {
        return kilometro;
    }
    
    public void setKilometro(String kilometro) {
        this.kilometro = kilometro;
    }
    
}