package com.gustavo.tinnova.utils;

import com.gustavo.tinnova.models.Veiculo;
import com.gustavo.tinnova.models.VeiculoT;

public class Mapper {

    public Veiculo mapVeiculotToVeiculo(VeiculoT veiculoT){
        return  new Veiculo(veiculoT.getId(),veiculoT.getVeiculo(),veiculoT.getMarca(), veiculoT.getAno(), veiculoT.getDescricao(), veiculoT.isVendido());
    }

    public VeiculoT mapVeiculoToVeiculoT(Veiculo veiculo){
        return  new VeiculoT(veiculo.getId(),veiculo.getVeiculo(),veiculo.getMarca(), veiculo.getAno(), veiculo.getDescricao(), veiculo.isVendido());
    }
}
