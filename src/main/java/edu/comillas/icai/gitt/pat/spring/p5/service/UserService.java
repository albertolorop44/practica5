package edu.comillas.icai.gitt.pat.spring.p5.service;

import edu.comillas.icai.gitt.pat.spring.p5.entity.AppUser;
import edu.comillas.icai.gitt.pat.spring.p5.entity.Token;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileRequest;
import edu.comillas.icai.gitt.pat.spring.p5.model.ProfileResponse;
import edu.comillas.icai.gitt.pat.spring.p5.model.RegisterRequest;
import edu.comillas.icai.gitt.pat.spring.p5.repository.TokenRepository;
import edu.comillas.icai.gitt.pat.spring.p5.repository.AppUserRepository;
import edu.comillas.icai.gitt.pat.spring.p5.util.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO#6
 * Completa los métodos del servicio para que cumplan con el contrato
 * especificado en el interface UserServiceInterface, utilizando
 * los repositorios y entidades creados anteriormente
 */

@Service
public class UserService implements UserServiceInterface {


    private final TokenRepository tokenRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    private Hashing hashing;

    public UserService(TokenRepository tokenRepository, AppUserRepository appUserRepository) {
        this.tokenRepository = tokenRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public Token login(String email, String password) {
        AppUser appUser = appUserRepository.findByEmail(email);
        if (appUser == null  || !hashing.compare(appUser.getPassword(), password))
            return null;

        Token tokenExistente = tokenRepository.findByAppUser(appUser);
        if (tokenExistente != null) return tokenExistente;

        Token token = new Token();
        token.setAppUser(appUser);
        return tokenRepository.save(token);
    }

    @Override
    public AppUser authentication(String tokenId) {
        Token token = tokenRepository.findById(tokenId).orElse(null);
        if(token == null) return null;
        return token.getAppUser();

    }

    @Override
    public ProfileResponse profile(AppUser appUser)
    {
        return new ProfileResponse(appUser.getName(), appUser.getEmail(), appUser.getRole());
    }


    @Override
    public ProfileResponse profile(AppUser appUser, ProfileRequest profile)
    {
        appUser.setName(profile.name());
        return new ProfileResponse(appUser.getName(), appUser.getEmail(), appUser.getRole());
    }

    @Override
    //respuesta con el perfil del nuevo usuario
    public ProfileResponse profile(RegisterRequest register)
    {
        AppUser nuevo = new AppUser();
        nuevo.setEmail(register.getEmail());
        nuevo.setPassword (hashing.hash(register.getPassword()));
        nuevo.setRole (Role.USER);
        nuevo.setName(register.getName());
        AppUser guardado = appUserRepository.save(nuevo);
        return new ProfileResponse(guardado.getName(), guardado.getEmail(), guardado.getRole());
    }

    @Override
    public void logout(String tokenId) {
        tokenRepository.deleteById(tokenId);

    }

    @Override
    public void delete(AppUser appUser) {
        appUserRepository.delete(appUser);
    }

}
