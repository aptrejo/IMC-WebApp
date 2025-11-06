package com.imc.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "mediciones")
public class Medicion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Usuario usuario;

    @Column(nullable=false)
    private double masaKg;

    @Column(nullable=false)
    private double imc;

    @Column(nullable=false)
    private LocalDateTime fechaHora = LocalDateTime.now();

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public double getMasaKg() { return masaKg; }
    public void setMasaKg(double masaKg) { this.masaKg = masaKg; }
    public double getImc() { return imc; }
    public void setImc(double imc) { this.imc = imc; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
}
