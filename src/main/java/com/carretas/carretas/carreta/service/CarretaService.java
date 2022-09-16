package com.carretas.carretas.carreta.service;

import com.carretas.carretas.carreta.entity.Carreta;
import com.carretas.carretas.carreta.repository.CarretaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarretaService {

    @Autowired
    private CarretaRepository carretaRepository;

    public void salvar(Carreta usuario) {
        carretaRepository.save(usuario);
    }

    public void setCarretaInfoById(String placa, String tipo, String volume, Integer id) {
        carretaRepository.setCarretaInfoById(placa, tipo, volume, id);
    }


    public List<Carreta> listar() {
        return carretaRepository.findAll();
    }

    public Carreta buscarPorId(Integer id) {
        return carretaRepository.findById(id).get();
    }

    public void excluir(Integer id) {
        carretaRepository.deleteById(id);
    }

}