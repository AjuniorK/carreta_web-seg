package com.carretas.carretas.carreta.service;

import com.carretas.carretas.carreta.entity.Cliente;
import com.carretas.carretas.carreta.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    @Autowired
    private Clientes clienteRepository;


    public void salvar(Cliente usuario) {
        clienteRepository.save(usuario);
    }

    public void atualizar(Cliente usuario) {
        clienteRepository.save(usuario);
    }

    public void deletar(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

    public void deletarByNome(String nome) {
        clienteRepository.deleteByNome(nome);
    }

    public void excluir(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente buscarPorId(Integer id) {
        return clienteRepository.findById(id).get();
    }

    public List<Cliente> buscarPorNome(String nome) {
        //clienteRepository.buscarPorNome(nome);
        return clienteRepository.encontrarPorNome("%" + nome +"%");
    }

    public List<Cliente> obterTodos() {
        return clienteRepository.findAll();
    }


}