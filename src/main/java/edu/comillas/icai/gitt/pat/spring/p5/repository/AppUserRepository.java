package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TODO#4
 * Crea el repositorio para la entidad AppUser de modo que,
 * además de las operaciones CRUD, se pueda consultar el AppUser asociado
 * a un email dado
 */

@Repository         //indica que esta interfaz se detecta como un repositorio
public interface AppUserRepository extends CrudRepository<AppUser, Long> {      //define que el repositorio trabaja con AppUser y claves primarias de tipo Long
    //metodo para buscar un usuario por su email
    Optional<AppUser> findByEmail(String email);

}