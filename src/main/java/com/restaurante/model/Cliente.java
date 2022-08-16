package com.restaurante.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteId;

    @Column
    private String email;

    @Column
    private String nome;

    @JsonIgnoreProperties("cliente")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente" ,cascade = CascadeType.ALL)
    private List<Pedido> pedido = new ArrayList<>();

}
