package com.academia.repository;

import com.academia.model.Aluno;

/** Repositório de Aluno. Herda todo o CRUD/serialização de Repositorio. */
public class AlunoRepository extends Repositorio<Aluno> {

    public AlunoRepository() {
        super("alunos.dat");
    }

    /** Consulta extra: verifica se já existe aluno com o CPF informado. */
    public boolean existeCpf(String cpf) {
        for (Aluno aluno : registros) {
            if (aluno.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
}
