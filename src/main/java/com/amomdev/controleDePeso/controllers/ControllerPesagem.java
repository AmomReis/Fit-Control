package com.amomdev.controleDePeso.controllers;

import com.amomdev.controleDePeso.model.Pesagem;
import com.amomdev.controleDePeso.services.ServicePesagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/minhas-pesagens")
public class ControllerPesagem {

    @Autowired
    private ServicePesagem servicePesagem;

    @GetMapping
    public ResponseEntity<List<Pesagem>> findAll() {
        return ResponseEntity.ok(servicePesagem.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pesagem> findById(@PathVariable Long id) {
        return ResponseEntity.ok(servicePesagem.findById(id));
    }


}
