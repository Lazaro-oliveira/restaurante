package com.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long entregaId;

    @Column
    private Date dataEntrega;

    @JsonIgnoreProperties("entrega")
    @OneToOne(fetch = FetchType.EAGER)
    private Pedido pedido;

}
