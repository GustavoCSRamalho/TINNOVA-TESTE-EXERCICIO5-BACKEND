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
            log.info("Preloading " + repository.save(new Veiculo("teste","Gol",2020,"muito bom",true)));
            log.info("Preloading " + repository.save(new Veiculo("teste","Gol",2020,"muito bom",false)));
//            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
        };
    }
}
