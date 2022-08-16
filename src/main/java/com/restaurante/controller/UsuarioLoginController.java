package com.restaurante.controller;

import com.restaurante.dto.AddClienteDto;
import com.restaurante.dto.AddUsuarioLoginDto;
import com.restaurante.model.Cliente;
import com.restaurante.model.UsuarioLogin;
import com.restaurante.service.UsuarioLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarioLogin")
public class UsuarioLoginController {

    @Autowired
    UsuarioLoginService usuarioLoginService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioLogin> listaUsuarioLogin(){
        return usuarioLoginService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioLogin salvar(@Valid @RequestBody AddUsuarioLoginDto addUsuarioLoginDto){

        UsuarioLogin usuarioLogin = new UsuarioLogin(null, addUsuarioLoginDto.getLogin(),passwordEncoder.encode(addUsuarioLoginDto.getPassword()));
        return usuarioLoginService.save(usuarioLogin);
    }

    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String login, @RequestParam String password) {

        Optional<UsuarioLogin> usuarioLogin = usuarioLoginService.getByLogin(login);
        if (!usuarioLogin.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        boolean valid = passwordEncoder.matches(password, usuarioLogin.get().getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

}
