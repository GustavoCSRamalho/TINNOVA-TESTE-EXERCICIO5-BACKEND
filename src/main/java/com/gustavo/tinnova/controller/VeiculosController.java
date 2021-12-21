package com.gustavo.tinnova.controller;

import com.gustavo.tinnova.models.Veiculo;
import com.gustavo.tinnova.models.VeiculoResposta;
import com.gustavo.tinnova.repository.VeiculoRepository;
import com.gustavo.tinnova.utils.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class VeiculosController {

    private final VeiculoRepository repository;
    private final Mapper mapper;

    VeiculosController(VeiculoRepository repository) {
        this.repository = repository;
        this.mapper = new Mapper();
    }

    @GetMapping("/veiculos")
    @ResponseBody
    public ResponseEntity<List<VeiculoResposta>> getVeiculos() {
        ResponseEntity<List<VeiculoResposta>> resposta ;
        try{
            resposta = ResponseEntity.ok(repository.findAll().stream().map(veiculo -> mapper.mapVeiculoToVeiculoT(veiculo)).collect(Collectors.toList()));
        }catch (Exception e){
            resposta = ResponseEntity.badRequest().body(null);
        }
        return resposta;
    }


    @GetMapping("/veiculos/find")  //TODO: Finalizar depois
    public ResponseEntity<List<VeiculoResposta>> getEncontrarVeiculo(@RequestParam("q") String atributo) {
        ResponseEntity<List<VeiculoResposta>> resposta ;
        List<VeiculoResposta> listaDeVeiculos = new ArrayList<>();

        try{
            listaDeVeiculos =  repository.findInAllAttributes(atributo).stream().map(veiculo -> mapper.mapVeiculoToVeiculoT(veiculo)).collect(Collectors.toList());

            resposta = ResponseEntity.ok(listaDeVeiculos);

        }catch (Exception e){
            resposta = ResponseEntity.badRequest().body(null);
        }

        return resposta;
    }

    @GetMapping("/veiculos/{id}")
    @ResponseBody
    public ResponseEntity<VeiculoResposta> getDetalheVeiculo(@PathVariable("id") Long veiculoId) {
        ResponseEntity<VeiculoResposta> resposta ;
        try{
            resposta = ResponseEntity.ok(mapper.mapVeiculoToVeiculoT(repository.findById(veiculoId).get()));
        }catch (Exception e){
            resposta = ResponseEntity.badRequest().body(null);
        }
        return resposta;
    }

    @PostMapping("/veiculos")
    public ResponseEntity<VeiculoResposta> postVeiculos(@RequestBody VeiculoResposta veiculoResposta) {
        ResponseEntity<VeiculoResposta> resposta ;

        try{
            Veiculo veiculo = mapper.mapVeiculotToVeiculo(veiculoResposta);
            Veiculo veiculoSalvo = repository.save(veiculo);

            veiculoResposta.setCreated(new Date());
            veiculoResposta.setUpdated(new Date());

            resposta = ResponseEntity.ok(mapper.mapVeiculoToVeiculoT(veiculoSalvo));
        }catch (Exception e){
            resposta = ResponseEntity.badRequest().body(null);
        }
        return resposta;
    }

    @PutMapping("/veiculos/{id}")
    public ResponseEntity<Optional<VeiculoResposta>> putAtualizarVeiculo(@PathVariable("id") Long veiculoId, @RequestBody VeiculoResposta veiculoResposta) {
        ResponseEntity<Optional<VeiculoResposta>> resposta ;

        try{
            resposta = ResponseEntity.ok(repository.findById(veiculoId)
                    .map(veiculo -> {
                        veiculo.setVeiculo(veiculoResposta.getVeiculo());
                        veiculo.setAno(veiculoResposta.getAno());
                        veiculo.setDescricao(veiculoResposta.getDescricao());
                        veiculo.setVendido(veiculoResposta.isVendido());
                        veiculo.setMarca(veiculoResposta.getMarca());
                        veiculo.setUpdated(new Date());
                        return mapper.mapVeiculoToVeiculoT(repository.save(veiculo));
                    }));
        }catch (Exception e){
            resposta = ResponseEntity.badRequest().body(null);
        }
        return resposta;
    }

    @PatchMapping("/veiculos/{id}")
    public ResponseEntity<Optional<VeiculoResposta>> patchAtualizarVeiculo(@PathVariable("id") Long veiculoId, @RequestBody VeiculoResposta veiculoResposta) {
        ResponseEntity<Optional<VeiculoResposta>> resposta ;
        try{
            resposta = ResponseEntity.ok(repository.findById(veiculoId)
                    .map(veiculo -> {
                        if(veiculoResposta.getVeiculo() != null) veiculo.setVeiculo(veiculoResposta.getVeiculo());
                        if(veiculoResposta.getAno() != 0) veiculo.setAno(veiculoResposta.getAno());
                        if (veiculoResposta.getDescricao() != null) veiculo.setDescricao(veiculoResposta.getDescricao());

                        if (veiculoResposta.isVendido()) veiculo.setVendido(veiculoResposta.isVendido());
                        if (veiculoResposta.getMarca() != null)  veiculo.setMarca(veiculoResposta.getMarca());
                        veiculo.setUpdated(new Date());
                        return mapper.mapVeiculoToVeiculoT(repository.save(veiculo));
                    }));
        }catch (Exception e){
            resposta = ResponseEntity.badRequest().body(null);
        }
        return resposta;
    }

    @DeleteMapping("/veiculos/{id}")
    public ResponseEntity deleteVeiculo(@PathVariable("id") Long veiculoId) {
        ResponseEntity resposta;
        try{
            repository.deleteById(veiculoId);
            resposta = ResponseEntity.ok("Deu certo");
        }
        catch (Exception e){
            resposta = ResponseEntity.badRequest().body(null);
        }
        return resposta;
    }
}
