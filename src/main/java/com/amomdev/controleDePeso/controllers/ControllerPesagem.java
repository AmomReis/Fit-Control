package com.amomdev.controleDePeso.controllers;

import org.springframework.ui.Model;
import com.amomdev.controleDePeso.model.Pesagem;
import com.amomdev.controleDePeso.services.ServicePesagem;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ControllerPesagem {

    @Autowired
    private ServicePesagem servicePesagem;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/pesagem/historico")
    public String historico(Model model) {
        model.addAttribute("pesagens", servicePesagem.findAll());
        return "historico"; // templates/historico.html
    }

    @GetMapping("/pesagem/{id}")
    @ResponseBody
    public ResponseEntity<Pesagem> getPesagem(@PathVariable Long id) {
        return ResponseEntity.ok(servicePesagem.findById(id));
    }

    @PostMapping("/pesagem/registrar")
    @ResponseBody
    public ResponseEntity<Void> registrar(
            @ModelAttribute Pesagem obj,
            @RequestParam(value = "usarHoje", required = false) Boolean usarHoje) {

        if (Boolean.TRUE.equals(usarHoje)) {
            obj.setData(java.time.LocalDate.now());
        } else if (obj.getData() == null) {
            // Evita salvar null
            obj.setData(java.time.LocalDate.now());
        }

        servicePesagem.insert(obj);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pesagem/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute Pesagem obj) {
        try {
            servicePesagem.update(id, obj);
            return "redirect:/pesagem/historico";
        } catch (EntityNotFoundException e) {
            return "redirect:/pesagem/historico?error=notfound";
        }
    }

    @GetMapping("/pesagem/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        servicePesagem.delete(id);
        return "redirect:/pesagem/historico";
    }

}
