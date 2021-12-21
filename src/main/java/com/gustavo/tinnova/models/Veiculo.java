package com.gustavo.tinnova.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
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
    private boolean vendido = false;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created ;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated")
    private Date updated ;


    public  Veiculo(Long id,String veiculo, String marca, int ano, String descricao, boolean vendido){
        this.id = id;
        this.veiculo = veiculo;
        this.marca = marca;
        this.ano = ano;
        this.descricao = descricao;
        this.vendido = vendido;

    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

//    @Override
//    public String toString() {
//        return "Veiculo{" + "id=" + this.id + ", vendido='" + this.vendido + '\'' + ", marca='" + this.marca + '\'' + '}';
//    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
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
