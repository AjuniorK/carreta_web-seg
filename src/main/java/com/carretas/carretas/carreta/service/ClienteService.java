package com.carretas.carretas.carreta.service;

import com.carretas.carretas.carreta.entity.Cliente;
import com.carretas.carretas.carreta.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void salvar(Cliente usuario) {
        clienteRepository.save(usuario);
    }

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).get();
    }

    public void excluir(Integer id) {
        clienteRepository.deleteById(id);
    }

}