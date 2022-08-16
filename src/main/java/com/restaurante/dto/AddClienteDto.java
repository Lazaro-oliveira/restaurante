package com.restaurante.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddClienteDto {

    @NotBlank(message = "E-mail é obrigatorio")
    @Email(message = "E-mail invalido")
    private String email;

    @NotBlank(message = "Nome é obrigatorio")
    private String nome;
}
