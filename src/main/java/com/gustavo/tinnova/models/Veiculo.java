package com.gustavo.tinnova.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Veiculo {
    private @Id
    @GeneratedValue
    Long id;

    public Veiculo(){}

    private String veiculo;

    private String marca;
    private int ano;
    private String descricao;
    private boolean vendido;
    public  Veiculo(String veiculo, String marca, int ano, String descricao, boolean vendido){
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.veiculo = veiculo;

    }

    @Override
    public String toString() {
        return "Veiculo{" + "id=" + this.id + ", veiculo='" + this.veiculo + '\'' + ", marca='" + this.marca + '\'' + '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
}
