package com.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.restaurante.model.Pedido;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddEntregaDto {

    @DateTimeFormat
    private Date dataEntrega;

    @NotNull(message = "Pedido Id é obrigatorio")
    private Long pedidoId;

}
