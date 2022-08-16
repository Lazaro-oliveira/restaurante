package com.restaurante.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddUsuarioLoginDto {

    @NotBlank(message = "Login é obrigatorio")
    private String login;

    @NotBlank(message = "Password é obrigatorio")
    private String password;
}
