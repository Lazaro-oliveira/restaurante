package com.restaurante.service;

import com.restaurante.data.DetalheUsuarioLoginData;
import com.restaurante.model.UsuarioLogin;
import com.restaurante.repository.UsuarioLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioLoginRepository usuarioLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioLogin> usuarioLogin = usuarioLoginRepository.findByLogin(username);
        if(!usuarioLogin.isPresent()){
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado");
        }
        return new DetalheUsuarioLoginData(usuarioLogin);
    }
}
