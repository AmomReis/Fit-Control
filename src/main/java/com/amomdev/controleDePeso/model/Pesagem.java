package com.amomdev.controleDePeso.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Pesagem implements Serializable {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter @Setter
    private Double massa;

    @Getter @Setter
    private LocalDate data;

    public Pesagem() {
    }

    public Pesagem(Double massa, LocalDate data) {
        this.massa = massa;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Pesagem pesagem = (Pesagem) o;
        return Objects.equals(id, pesagem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
