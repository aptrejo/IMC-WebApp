package com.imc.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity @Table(name = "usuarios")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max=120)
    private String nombreCompleto;

    @NotBlank @Size(max=50) @Column(unique = true)
    private String nombreUsuario;

    @NotBlank @Size(min=6, max=255)
    private String contrasenaHash;

    @Min(15)
    private int edad;

    @NotBlank
    private String sexo; // M/F/O

    @DecimalMin("1.0") @DecimalMax("2.5")
    private double estaturaM;

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getContrasenaHash() { return contrasenaHash; }
    public void setContrasenaHash(String contrasenaHash) { this.contrasenaHash = contrasenaHash; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public double getEstaturaM() { return estaturaM; }
    public void setEstaturaM(double estaturaM) { this.estaturaM = estaturaM; }
}
