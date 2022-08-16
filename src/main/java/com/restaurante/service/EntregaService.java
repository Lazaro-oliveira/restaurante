package com.restaurante.service;

import com.restaurante.model.Entrega;
import com.restaurante.model.Pedido;
import com.restaurante.repository.EntregaRepository;
import com.restaurante.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {
    @Autowired
    EntregaRepository entregaRepository;

    public Entrega save(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public Optional<Entrega> getById(Long entregaId) {
        return entregaRepository.findById(entregaId);
    }

    public List<Entrega> getAll() {
        return entregaRepository.findAll();
    }

    public void deleteById(Long entregaId) {
        entregaRepository.deleteById(entregaId);
    }

}
