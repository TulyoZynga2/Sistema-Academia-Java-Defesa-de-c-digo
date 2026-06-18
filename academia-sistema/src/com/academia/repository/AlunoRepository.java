package com.academia.repository;

import com.academia.model.Aluno;


public class AlunoRepository extends Repositorio<Aluno> {

    public AlunoRepository() {
        super("alunos.dat");
    }

    
    public boolean existeCpf(String cpf) {
        for (Aluno aluno : registros) {
            if (aluno.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}
