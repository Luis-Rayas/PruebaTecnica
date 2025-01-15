package com.estafanini.pruebatecnica.LRCRR.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
@Builder
@SQLDelete(sql = "UPDATE users SET fh_eliminacion = current_timestamp WHERE id=?")

public class User {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fh_creacion", nullable = false )
    private LocalDateTime fh_creacion;

    @Column(name = "fh_modificacion")
    private LocalDateTime fh_modificacion;

    @Column(name = "fh_eliminacion")
    private LocalDateTime fh_eliminacion;

    @PrePersist
    public void prePersist() { //Antes de Crear el registro
        this.fh_creacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() { //Antes de Actualizar el registro
        this.fh_modificacion = LocalDateTime.now();
    }
    public User() {
    }

    public User(Long id, String nombre, String email, String password, LocalDateTime fh_creacion, LocalDateTime fh_modificacion, LocalDateTime fh_eliminacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fh_creacion = fh_creacion;
        this.fh_modificacion = fh_modificacion;
        this.fh_eliminacion = fh_eliminacion;
    }
}
