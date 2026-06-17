package com.academia.repository;

import com.academia.model.Treino;

public class TreinoRepository extends Repositorio<Treino> {

    public TreinoRepository() {
        super("treinos.dat");
    }
}
