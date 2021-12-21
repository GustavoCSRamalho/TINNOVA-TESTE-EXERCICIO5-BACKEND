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

    //Cria um construtor para fazer a instancia dos componentes.
    VeiculosController(VeiculoRepository repository) {
        this.repository = repository;
        this.mapper = new Mapper();
    }

    /**
     * Cria a rota localhost:8080/veiculos que torna todos os veiculos no banco de dados
     *
      * @return
     */
    @GetMapping("/veiculos")
    @ResponseBody
    public ResponseEntity<List<VeiculoResposta>> getVeiculos() {
        ResponseEntity<List<VeiculoResposta>> resposta ;
        try{
            //Faz a busca de todos os veiculos no banco de dados
            List<Veiculo> listaDeVeiculos = repository.findAll();
            //Mapeia todos o objetos do tipo Veiculo para VeiculoResposta para poder serem enviado ao cliente
            List<VeiculoResposta> listaDeVeiculosMapeados = listaDeVeiculos.stream().map(veiculo -> mapper.mapVeiculoToVeiculoT(veiculo)).collect(Collectors.toList());
            //Envia a resposta para o usuario com os objetos.
            resposta = ResponseEntity.ok(listaDeVeiculosMapeados);
        }catch (Exception e){
            //Retorna uma resposta de erro caso algo fora do comum aconteca.
            resposta = ResponseEntity.badRequest().body(null);
        }
        //Retorna a resposta de acordo com o ocorrido
        return resposta;
    }

    /**
     * Cria a rota localhost:8080/veiculos/find?q=Gol que torna todos os veiculos que tem a palavra Gol
     *
     * @return
     */
    @GetMapping("/veiculos/find")  //TODO: Finalizar depois
    public ResponseEntity<List<VeiculoResposta>> getEncontrarVeiculo(@RequestParam("q") String atributo) {
        ResponseEntity<List<VeiculoResposta>> resposta ;
        List<VeiculoResposta> listaDeVeiculos = new ArrayList<>();

        try{
            listaDeVeiculos =  repository.findInAllAttributes(atributo).stream().map(veiculo -> mapper.mapVeiculoToVeiculoT(veiculo)).collect(Collectors.toList());

            resposta = ResponseEntity.ok(listaDeVeiculos);

        }catch (Exception e){
            //Retorna uma resposta de erro caso algo fora do comum aconteca.
            resposta = ResponseEntity.badRequest().body(null);
        }
        //Retorna a resposta de acordo com o ocorrido
        return resposta;
    }

    /**
     * Cria a rota localhost:8080/veiculos/id que torna os detalhes do veiculo com o ID passado
     *
     * @return
     */
    @GetMapping("/veiculos/{id}")
    @ResponseBody
    public ResponseEntity<VeiculoResposta> getDetalheVeiculo(@PathVariable("id") Long veiculoId) {
        ResponseEntity<VeiculoResposta> resposta ;
        try{
            resposta = ResponseEntity.ok(mapper.mapVeiculoToVeiculoT(repository.findById(veiculoId).get()));
        }catch (Exception e){
            //Retorna uma resposta de erro caso algo fora do comum aconteca.
            resposta = ResponseEntity.badRequest().body(null);
        }
        //Retorna a resposta de acordo com o ocorrido
        return resposta;
    }

    /**
     * Cria a rota localhost:8080/veiculos que passa um objeto do tipo veiculo no body e faz o casdastro
     * no banco de dados.
     *
     * @return
     */
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
            //Retorna uma resposta de erro caso algo fora do comum aconteca.
            resposta = ResponseEntity.badRequest().body(null);
        }
        //Retorna a resposta de acordo com o ocorrido
        return resposta;
    }

    /**
     * Cria a rota localhost:8080/veiculos/id que atualiza todos os dados do veiculo com o
     * id passado.
     *
     * @return
     */
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
            //Retorna uma resposta de erro caso algo fora do comum aconteca.
            resposta = ResponseEntity.badRequest().body(null);
        }
        //Retorna a resposta de acordo com o ocorrido
        return resposta;
    }

    /**
     * Cria a rota localhost:8080/veiculos/id que atualiza so os dados alterados do
     * id passado.
     *
     * @return
     */
    @PatchMapping("/veiculos/{id}")
    public ResponseEntity<Optional<VeiculoResposta>> patchAtualizarVeiculo(@PathVariable("id") Long veiculoId, @RequestBody VeiculoResposta veiculoResposta) {
        ResponseEntity<Optional<VeiculoResposta>> resposta ;
        try{
            //Ecnontra o veiculo de acordo com id e faz as mudancas dos itens que foram
            //alterados
            Optional<Veiculo> veiculoEncontrado = repository.findById(veiculoId);
            Optional<VeiculoResposta> veiculoAlterado = veiculoEncontrado
                    .map(veiculo -> {
                        if (veiculoResposta.getVeiculo() != null) veiculo.setVeiculo(veiculoResposta.getVeiculo());
                        if (veiculoResposta.getAno() != 0) veiculo.setAno(veiculoResposta.getAno());
                        if (veiculoResposta.getDescricao() != null)
                            veiculo.setDescricao(veiculoResposta.getDescricao());

                        if (veiculoResposta.isVendido()) veiculo.setVendido(veiculoResposta.isVendido());
                        if (veiculoResposta.getMarca() != null) veiculo.setMarca(veiculoResposta.getMarca());
                        veiculo.setUpdated(new Date());
                        return mapper.mapVeiculoToVeiculoT(repository.save(veiculo));
                    });
            //Seta a resposta de acordo com a resposta
            resposta = ResponseEntity.ok(veiculoAlterado);
        }catch (Exception e){
            //Retorna uma resposta de erro caso algo fora do comum aconteca.
            resposta = ResponseEntity.badRequest().body(null);
        }
        //Retorna a resposta de acordo com o ocorrido
        return resposta;
    }

    /**
     * Cria a rota localhost:8080/veiculos/id que deleta o veiculo com o
     * id passado.
     *
     * @return
     */
    @DeleteMapping("/veiculos/{id}")
    public ResponseEntity deleteVeiculo(@PathVariable("id") Long veiculoId) {
        ResponseEntity resposta;
        try{
            //Deleta do banco de dados o objeto com o ID passado.
            repository.deleteById(veiculoId);
            //Retorna uma resposta para o usuario.
            resposta = ResponseEntity.ok().body("");
        }
        catch (Exception e){
            //Retorna uma resposta de erro caso algo fora do comum aconteca.
            resposta = ResponseEntity.badRequest().body(null);
        }
        //Retorna a resposta de acordo com o ocorrido
        return resposta;
    }
}
