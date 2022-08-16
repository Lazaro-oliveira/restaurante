package com.restaurante.controller;

import com.restaurante.dto.AddUsuarioLoginDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @ApiOperation("Login.")
    @PostMapping("/login")
    public void fakeLogin(@RequestBody AddUsuarioLoginDto addUsuarioLoginDto) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

}
