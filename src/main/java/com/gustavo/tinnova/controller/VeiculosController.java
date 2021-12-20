package com.gustavo.tinnova.controller;

import com.gustavo.tinnova.models.Veiculo;
import com.gustavo.tinnova.models.VeiculoT;
import com.gustavo.tinnova.repository.VeiculoRepository;
import com.gustavo.tinnova.utils.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VeiculosController {

    private final VeiculoRepository repository;
    private final Mapper mapper;

    VeiculosController(VeiculoRepository repository) {
        this.repository = repository;
        this.mapper = new Mapper();
    }


    @GetMapping("/hello-world")

    public ResponseEntity<String> get() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/veiculos")
    @ResponseBody
    public ResponseEntity<List<Veiculo>> getVeiculos() {
        return ResponseEntity.ok(repository.findAll());
    }


    @GetMapping("/veiculos/find")  //TODO: Finalizar depois
    public ResponseEntity<String> getEncontrarVeiculo(@RequestParam("q") String atributo) {
//        List<VeiculoT>listaDeVeiculos = (List<VeiculoT>) repository.findAllByAttribute(atributo).stream().map(veiculo -> mapper.mapVeiculoToVeiculoT(veiculo));
        return ResponseEntity.ok("");
    }

    @GetMapping("/veiculos/{id}")
    @ResponseBody
    public ResponseEntity<VeiculoT> getDetalheVeiculo(@PathVariable("id") Long veiculoId) {
        return ResponseEntity.ok(mapper.mapVeiculoToVeiculoT(repository.findById(veiculoId).get()));
    }

    @PostMapping("/veiculos")
    public ResponseEntity<Veiculo> postVeiculos(@RequestBody VeiculoT veiculoT) {
        return ResponseEntity.ok(repository.save(mapper.mapVeiculotToVeiculo(veiculoT)));
    }

    @PutMapping("/veiculos/{id}")
    public ResponseEntity<Optional<Veiculo>> putAtualizarVeiculo(@PathVariable("id") Long veiculoId, @RequestBody VeiculoT veiculoT) {
        return ResponseEntity.ok(repository.findById(veiculoId)
                .map(veiculo -> {
                    veiculo.setVeiculo(veiculoT.getVeiculo());
                    veiculo.setAno(veiculoT.getAno());
                    veiculo.setDescricao(veiculoT.getDescricao());
                    veiculo.setVendido(veiculoT.isVendido());
                    veiculo.setMarca(veiculoT.getMarca());
                    return repository.save(veiculo);
                }));
    }

    @PatchMapping("/veiculos/{id}")
    public ResponseEntity<Optional<Veiculo>> patchAtualizarVeiculo(@PathVariable("id") Long veiculoId, @RequestBody VeiculoT veiculoT) {
        return ResponseEntity.ok(repository.findById(veiculoId)
                .map(veiculo -> {
                    if(veiculoT.getVeiculo() != null) veiculo.setVeiculo(veiculoT.getVeiculo());
                    if(veiculoT.getAno() != 0) veiculo.setAno(veiculoT.getAno());
                    if (veiculoT.getDescricao() != null) veiculo.setDescricao(veiculoT.getDescricao());

                    if (veiculoT.isVendido()) veiculo.setVendido(veiculoT.isVendido());
                    if (veiculoT.getMarca() != null)  veiculo.setMarca(veiculoT.getMarca());
                    return repository.save(veiculo);
                }));
    }

    @DeleteMapping("/veiculos/{id}")
    public ResponseEntity deleteVeiculo(@PathVariable("id") Long veiculoId) {
        try{
            repository.deleteById(veiculoId);
        }
        catch (Exception e){

        }
        return ResponseEntity.ok("Deu certo");
    }
}
