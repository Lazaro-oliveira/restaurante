package com.restaurante.service;

import com.restaurante.model.Cliente;
import com.restaurante.model.Pedido;
import com.restaurante.model.UsuarioLogin;
import com.restaurante.repository.UsuarioLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioLoginService {

    @Autowired
    UsuarioLoginRepository usuarioLoginRepository;

    public List<UsuarioLogin> getAll() {
        return usuarioLoginRepository.findAll();
    }
    public UsuarioLogin save(UsuarioLogin usuarioLogin) {
        return usuarioLoginRepository.save(usuarioLogin);
    }

    public Optional<UsuarioLogin> getByLogin(String login) {return usuarioLoginRepository.findByLogin(login); }

}
