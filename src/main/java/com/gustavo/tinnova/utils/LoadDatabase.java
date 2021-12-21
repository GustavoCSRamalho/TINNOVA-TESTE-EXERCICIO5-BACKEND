package com.gustavo.tinnova.utils;


import com.gustavo.tinnova.models.Veiculo;
import com.gustavo.tinnova.repository.VeiculoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(VeiculoRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","AGCO",2019,"muito bom",true)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Caterpillar",2020,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","CNH New Holland,",2020,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Chery",2015,"muito bom",true)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Ford",2015,"muito bom",true)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Chevrolet",2020,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Honda",2019,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Hyundai",2020,"muito bom",true)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Komatsu",2019,"muito bom",true)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Mercedes-Benz",2020,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Scania",2019,"muito bom",true)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Toyota",2019,"muito bom",true)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","Valtra",2015,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","VW",2015,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Veiculo(Long.getLong("0"),"teste","John Deere",2015,"muito bom",false)));
        };
    }
}
