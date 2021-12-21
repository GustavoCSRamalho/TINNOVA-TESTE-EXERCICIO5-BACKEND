package com.gustavo.tinnova.repository;


import com.gustavo.tinnova.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long> {

    //Faz a busca do veiculo por mais de um atributo.
    @Query("select v from Veiculo v where v.descricao = ?1 or v.veiculo = ?1" +
            "  or v.marca = ?1 ")
    public List<Veiculo> findInAllAttributes(String attribute);
}
