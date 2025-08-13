package com.amomdev.controleDePeso.services;

import com.amomdev.controleDePeso.model.Pesagem;
import com.amomdev.controleDePeso.repositories.RepositoryPesagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePesagem {

    @Autowired
    private RepositoryPesagem repositoryPesagem;

    public Pesagem insert(Pesagem p) {
        return repositoryPesagem.save(p);
    }

    public void delete(Long id) {
        if(!repositoryPesagem.existsById(id)){
            throw new RuntimeException("Esse id: " + id + "não pertence a nenhuma pesagem");
        }

        try {
            repositoryPesagem.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException(e);
        }
    }

    public Pesagem update(Long id, Pesagem obj) {
        return repositoryPesagem.findById(id)
                .map(entity -> {
                    entity.setData(obj.getData());
                    entity.setMassa(obj.getMassa());
                    return repositoryPesagem.save(entity);
                })
                .orElseThrow(RuntimeException::new);
    }

    public List<Pesagem> findAll() {
        return repositoryPesagem.findAll();
    }

    public Pesagem findById(Long id) {
        return repositoryPesagem.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
