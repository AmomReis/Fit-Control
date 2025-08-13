package com.amomdev.controleDePeso.config;

import com.amomdev.controleDePeso.model.Pesagem;
import com.amomdev.controleDePeso.repositories.RepositoryPesagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
@Profile("h2")
public class Teste implements CommandLineRunner {

    @Autowired
    private RepositoryPesagem repositoryPesagem;

    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate data = LocalDate.parse("10/08/2025", fmt);

    @Override
    public void run(String... args) throws Exception {
        Pesagem p1 = new Pesagem(90.0, data);
        Pesagem p2 = new Pesagem(89.0, LocalDate.parse("11/08/2025", fmt));
        Pesagem p3 = new Pesagem(87.0, LocalDate.now());

        repositoryPesagem.saveAll(Arrays.asList(p1, p2, p3));
    }
}
