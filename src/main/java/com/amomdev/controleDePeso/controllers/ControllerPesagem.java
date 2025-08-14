package com.amomdev.controleDePeso.controllers;

import com.amomdev.controleDePeso.model.Pesagem;
import com.amomdev.controleDePeso.services.ServicePesagem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/home")
public class ControllerPesagem {

    @Autowired
    private ServicePesagem servicePesagem;

    @GetMapping
    public ResponseEntity<List<Pesagem>> findAll() {
        return ResponseEntity.ok(servicePesagem.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pesagem> findById(@PathVariable Long id) {
        return ResponseEntity.ok(servicePesagem.findById(id));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Pesagem> insert(@RequestBody Pesagem obj) {
        obj = servicePesagem.insert(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicePesagem.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pesagem> update(@PathVariable Long id, @RequestBody Pesagem obj) {
        try {
            var updated = servicePesagem.update(id, obj);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
