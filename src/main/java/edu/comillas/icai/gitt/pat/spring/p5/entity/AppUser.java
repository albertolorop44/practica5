package edu.comillas.icai.gitt.pat.spring.p5.entity;

import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.*;

/**
 * TODO#2
 * Completa la entidad AppUser (cuya tabla en BD se llamará APP_USER)
 * para que tenga los siguientes campos obligatorios:
 * - id, que será la clave primaria numérica y autogenerada
 * - email, que debe tener valores únicos en toda la tabla
 * - password
 * - role, modelado con la clase Role ya existente en el proyecto
 * - name
 */
@Entity                 //entidad JPA, tabla de base de datos
@Table(name="APP_USER")     //nombre de la tabla: APP_USER

public class AppUser {          //cada instancia una fila en la tabla
    @Id         //clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //el valor de id se autogenera
    private Long id;        //tipo de dato que usaremos para identificar a cada usuario

    @Column(nullable = false, unique = true)    //no puede ser nulo, y no puede haber dos usuarios con el mismo email
    private String email;       //guarda el correo electrónico del usuario

    @Column(nullable = false)
    private String password;    //contraseña no puede ser nula

    @Enumerated(EnumType.STRING)    //role se guarda como texto en la tabla
    @Column(nullable = false)   //obligatorio
    private Role role;          //rol del usuario, admin, user...

    @Column(nullable = false)       //nombre del usuario
    private String name;

//getters y setters para acceder y modificar los campos privados de la clase

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }






}