package com.academia.repository;

import com.academia.model.Instrutor;

public class InstrutorRepository extends Repositorio<Instrutor> {

    public InstrutorRepository() {
        super("instrutores.dat");
    }
}
