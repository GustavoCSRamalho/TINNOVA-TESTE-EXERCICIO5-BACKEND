package com.gustavo.tinnova.repository;


import com.gustavo.tinnova.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoRepository  extends JpaRepository<Veiculo, Long> {

    public List<Veiculo> findAllByDescricao(String attribute);
//    public List<IdOnly> findAllByName(String name);
}
