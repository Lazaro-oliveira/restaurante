package com.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;

    @Column
    private Date dataPedido;

    @Column
    private String item;

    @JsonIgnoreProperties("pedido")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;

    @JsonIgnoreProperties("pedido")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pedido" ,cascade = CascadeType.ALL)
    private Entrega entrega;

}
