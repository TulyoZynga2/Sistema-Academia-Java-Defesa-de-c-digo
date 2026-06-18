package com.academia.controller;

import com.academia.model.Treino;
import com.academia.repository.TreinoRepository;
import com.academia.util.exceptions.DadosInvalidosException;

import java.time.LocalDate;
import java.util.List;


public class TreinoController {

    private final TreinoRepository repository = new TreinoRepository();
    private final AlunoController alunoController;
    private final InstrutorController instrutorController;
    private final ExercicioController exercicioController;

    public TreinoController(AlunoController alunoController,
                            InstrutorController instrutorController,
                            ExercicioController exercicioController) {
        this.alunoController = alunoController;
        this.instrutorController = instrutorController;
        this.exercicioController = exercicioController;
    }

    public Treino cadastrar(String descricao, LocalDate data,
                            int alunoId, int instrutorId) {
        if (descricao == null || descricao.isBlank()) {
            throw new DadosInvalidosException("A descricao do treino e obrigatoria.");
        }
        
        alunoController.exigirAluno(alunoId);
        instrutorController.exigirInstrutor(instrutorId);

        Treino treino = new Treino(descricao, data, alunoId, instrutorId);
        return repository.salvar(treino);
    }

    public void adicionarExercicio(int treinoId, int exercicioId) {
        Treino treino = repository.buscarPorId(treinoId);
        
        if (!exercicioController.existe(exercicioId)) {
            throw new DadosInvalidosException(
                    "Exercicio " + exercicioId + " nao existe no catalogo.");
        }
        treino.adicionarExercicio(exercicioId);
        repository.atualizar(treino);
    }

    public void removerExercicio(int treinoId, int exercicioId) {
        Treino treino = repository.buscarPorId(treinoId);
        treino.removerExercicio(exercicioId);
        repository.atualizar(treino);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }

    public Treino buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Treino> listar() {
        return repository.listarTodos();
    }
}
