package com.restaurante.repository;

import com.restaurante.model.UsuarioLogin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioLoginRepository extends JpaRepository<UsuarioLogin, Long> {

    public Optional<UsuarioLogin> findByLogin(String login);
}
