package com.academia.repository;

import com.academia.model.Exercicio;

public class ExercicioRepository extends Repositorio<Exercicio> {

    public ExercicioRepository() {
        super("exercicios.dat");
    }
}
