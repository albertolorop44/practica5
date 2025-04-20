package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import jakarta.persistence.Entity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * TODO#5
 * Crea el repositorio para la entidad Token de modo que,
 * además de las operaciones CRUD, se pueda consultar el Token asociado
 * a un AppUser dado
 */

@Repository         //esta interfaz como un repositorio
public interface TokenRepository extends CrudRepository<Token, String> {
    //consulta el token asociado a un usuario especifico
    Optional<Token> findByAppUser(AppUser appUser);

//lo he puesto optional ya que puede ser que el usuario no tenga ningun token asociado, por lo que asi puede ser null
}