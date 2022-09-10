package com.carretas.carretas.carreta.entity;

import javax.persistence.*;

@Entity
@Table(name = "CARRETA")
public class Carreta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "PLACA", nullable = false, unique = true)
    private String placa;

    @Column(name = "VOLUME", nullable = false)
    private String volume;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}