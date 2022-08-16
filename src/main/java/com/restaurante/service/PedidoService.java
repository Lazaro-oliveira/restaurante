package com.restaurante.service;

import com.restaurante.model.Pedido;
import com.restaurante.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> getById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId);
    }

    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }

    public void deleteById(Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }
}
