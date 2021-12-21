package com.gustavo.tinnova.utils;

import com.gustavo.tinnova.models.Veiculo;
import com.gustavo.tinnova.models.VeiculoResposta;

public class Mapper {

    public Veiculo mapVeiculotToVeiculo(VeiculoResposta veiculoResposta){
        Veiculo veiculo = new Veiculo(veiculoResposta.getId(), veiculoResposta.getVeiculo(), veiculoResposta.getMarca(), veiculoResposta.getAno(), veiculoResposta.getDescricao(), veiculoResposta.isVendido());
        veiculo.setCreated(veiculoResposta.getCreated());
        veiculo.setUpdated(veiculoResposta.getUpdated());
        return veiculo;
    }

    public VeiculoResposta mapVeiculoToVeiculoT(Veiculo veiculo){
        VeiculoResposta veiculoResposta = new VeiculoResposta(veiculo.getId(), veiculo.getVeiculo(), veiculo.getMarca(), veiculo.getAno(), veiculo.getDescricao(), veiculo.isVendido());
        veiculoResposta.setCreated(veiculo.getCreated());
        veiculoResposta.setUpdated(veiculo.getUpdated());
        return veiculoResposta;
    }
}
