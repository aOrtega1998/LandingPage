package com.example.springjpa.entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "USUARIOS")
public class Usuarios implements Serializable {

    @Id
    @Column(name = "ID_USUARIO", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(name = "TX_NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "TX_APELLIDO", nullable = false)
    private String apellido;

    @Column(name = "TX_EMAIL")
    private String email;

    @Column(name = "NUM_CORREO")
    private Integer numEmail;


    public Usuarios(String nombre, String apellido, String email, Integer numEmail) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.numEmail = numEmail;
    }

    public Usuarios() {

    }
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumEmail() {return numEmail;}

    public void setNumEmail(Integer numEmail) {this.numEmail = numEmail;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return  Objects.equals(idUsuario, usuarios.idUsuario)
                && Objects.equals(nombre, usuarios.nombre)
                && Objects.equals(apellido, usuarios.apellido)
                && Objects.equals(email, usuarios.email)
                && Objects.equals(numEmail, usuarios.numEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombre, apellido, email, numEmail);
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", numEmail='" + numEmail + '\'' +
                '}';
    }
}



