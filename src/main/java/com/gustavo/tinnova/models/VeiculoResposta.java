package com.gustavo.tinnova.models;

import java.util.Date;

public class VeiculoResposta {

    private Long id;

    public VeiculoResposta(){}

    private String veiculo;

    private String marca;
    private int ano;
    private String descricao;
    private Boolean vendido;
    private Date created ;
    private Date updated ;
    public VeiculoResposta(Long id , String veiculo, String marca, int ano, String descricao, Boolean vendido){
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;

    }

    public Boolean getVendido() {
        return vendido;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
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

    public Boolean isVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }
}
