package edu.comillas.icai.gitt.pat.spring.p5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;

/**
 * TODO#3
 * Completa la entidad Token (cuya tabla en BD se llamará TOKEN)
 * para que, además de la clave primaria ya indicada (cadena autogenerada aleatoria representando la sesión),
 * tenga un campo appUser, que represente la asociación uno a uno con la entidad AppUser (el usuario asociado a la sesión).
 * Este campo deberá configurarse para que en caso de que se borre el usuario, también se borre su sesión asociada: borrado en cascada
 */

@Entity         //esta clase como entidad JPA para que se mape en una talba llamada TOKEN
public class Token {
    @Id     //clave primaria ID, se genera como cadena unica, UUID
//genera automaticamente un identificador unico de sesión
    @GeneratedValue(strategy = GenerationType.UUID)
    public String id;

//cada token asociado a un unico usuario
    @OneToOne(optional = false)         //relacion uno a uno obligatoria, cada token esta asociado a un único AppUser
    @JoinColumn(name = "user_id", nullable = false)     //clave foránea que apunta a la tabla APP_USER
//borrado cascada
    @OnDelete(action = OnDeleteAction.CASCADE)      //si se borra el usuario, se borra tambien su token, borrado en cascada

    private AppUser appUser;

//getters y setters, para leer y escribir los campos

    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }

    public AppUser getAppUser(){
        return appUser;
    }
    public void setAppUser(AppUser appUser){
        this.appUser = appUser;
    }
}
