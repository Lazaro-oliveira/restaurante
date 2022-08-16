package com.restaurante.dto;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddPedidoDto {

    @DateTimeFormat
    private Date dataPedido;

    @NotBlank(message = "Item é obrigatorio")
    private String item;

    @NotNull(message = "ClienteId é obrigatorio")
    private Long clienteId;

}
