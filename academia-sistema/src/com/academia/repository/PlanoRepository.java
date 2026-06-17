package com.academia.repository;

import com.academia.model.Plano;

public class PlanoRepository extends Repositorio<Plano> {

    public PlanoRepository() {
        super("planos.dat");
    }
}
