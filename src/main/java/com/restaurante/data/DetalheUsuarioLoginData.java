package com.restaurante.data;

import com.restaurante.model.UsuarioLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioLoginData implements UserDetails {

    private final Optional<UsuarioLogin> usuarioLogin;

    public DetalheUsuarioLoginData(Optional<UsuarioLogin> usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuarioLogin.orElse(new UsuarioLogin()).getPassword();
    }

    @Override
    public String getUsername() {
        return usuarioLogin.orElse(new UsuarioLogin()).getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
