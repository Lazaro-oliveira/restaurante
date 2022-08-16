package com.restaurante.service;

import com.restaurante.model.Cliente;
import com.restaurante.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> getById(Long clienteId) {
        return clienteRepository.findById(clienteId);
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public void deleteById(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}
