package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.Role;
import org.hibernate.validator.internal.constraintvalidators.bv.AssertTrueValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@DataJpaTest
class RepositoryIntegrationTest {
    @Autowired TokenRepository tokenRepository;
    @Autowired AppUserRepository appUserRepository;
    @Autowired
    private AssertTrueValidator assertTrueValidator;

    /**
     * TODO#9
     * Completa este test de integración para que verifique
     * que los repositorios TokenRepository y AppUserRepository guardan
     * los datos correctamente, y las consultas por AppToken y por email
     * definidas respectivamente en ellos retornan el token y usuario guardados.
     */
    @Test
    void saveTest() {
        // Given ...
        AppUser user = new AppUser();
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setName("test");
        user.setRole(Role.USER);

        AppUser savedUser = appUserRepository.save(user);

        Token token = new Token();
        token.setAppUser(savedUser);
        Token savedToken = tokenRepository.save(token);

        // When ...
        Optional<AppUser> userByEmail = appUserRepository.findByEmail("test@test.com");
        Optional<Token> tokenById = tokenRepository.findById(savedToken.getId());
        Optional<Token> tokenByUser = tokenRepository.findByAppUser(savedUser);

        // Then ...
        assertTrue(userByEmail.isPresent());
        assertEquals(savedUser.getId(), userByEmail.get().getId());

        assertTrue(tokenById.isPresent());
        assertEquals(savedToken.getId(), tokenById.get().getId());

        assertTrue(tokenByUser.isPresent());
        assertEquals(savedToken.getId(), tokenByUser.get().getId());
    }

    /**
     * TODO#10
     * Completa este test de integración para que verifique que
     * cuando se borra un usuario, automáticamente se borran sus tokens asociados.
     */
    @Test       //test para ser ejecutado automaticamente cuando lancemos los test
    void deleteCascadeTest() {
        // Given ...
        AppUser user = new AppUser();       //creamos un nuevo objeto
//asignamos valores a los campos requeridos del usuario
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setName("test");
        user.setRole(Role.USER);
   //guardamos el AppUser. se devuelve el objeto guardado con su id asignado
        AppUser savedUser = appUserRepository.save(user);
//creamos un nuevo token  y lo asociamos al uppuser anterior
        Token token = new Token();
        token.setAppUser(savedUser);
        tokenRepository.save(token);

        // When ...
  //se borrara el usuario y por la configuracion tambien el token
        appUserRepository.delete(savedUser);

        // Then ...
  //comprueba que no queda ningún AppUser ni Tolen en la base de datos tras borrarlo
        Assertions.assertEquals(0, appUserRepository.count());
        Assertions.assertEquals(0, tokenRepository.count());

    }
}