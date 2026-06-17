package com.academia.view;

import com.academia.controller.AlunoController;
import com.academia.controller.ExercicioController;
import com.academia.controller.InstrutorController;
import com.academia.controller.TreinoController;
import com.academia.model.Exercicio;
import com.academia.model.Treino;
import com.academia.util.InputUtil;

import java.time.LocalDate;
import java.util.List;

/** View do CRUD de Treinos. Resolve as FKs para exibir nomes ao usuário. */
public class TreinoView {

    private final TreinoController controller;
    private final AlunoController alunoController;
    private final InstrutorController instrutorController;
    private final ExercicioController exercicioController;

    public TreinoView(TreinoController controller,
                      AlunoController alunoController,
                      InstrutorController instrutorController,
                      ExercicioController exercicioController) {
        this.controller = controller;
        this.alunoController = alunoController;
        this.instrutorController = instrutorController;
        this.exercicioController = exercicioController;
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- TREINOS ---");
            System.out.println("1 - Cadastrar treino");
            System.out.println("2 - Listar treinos");
            System.out.println("3 - Adicionar exercicio ao treino");
            System.out.println("4 - Remover exercicio do treino");
            System.out.println("5 - Deletar treino");
            System.out.println("0 - Voltar");
            opcao = InputUtil.lerInteiro("Opcao");

            try {
                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> adicionarExercicio();
                    case 4 -> removerExercicio();
                    case 5 -> deletar();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (RuntimeException e) {
                System.out.println(">> Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
        String descricao = InputUtil.lerTexto("Descricao do treino");
        LocalDate data = InputUtil.lerData("Data");
        int alunoId = InputUtil.lerInteiro("Id do aluno (FK)");
        int instrutorId = InputUtil.lerInteiro("Id do instrutor (FK)");
        Treino treino = controller.cadastrar(descricao, data, alunoId, instrutorId);
        System.out.println("Treino criado com id " + treino.getId()
                + ". Adicione exercicios pela opcao 3.");
    }

    private void listar() {
        List<Treino> treinos = controller.listar();
        if (treinos.isEmpty()) {
            System.out.println("Nenhum treino cadastrado.");
            return;
        }
        for (Treino treino : treinos) {
            // Resolve as chaves estrangeiras para exibir nomes legíveis.
            String aluno = alunoController.buscarPorId(treino.getAlunoId()).getNome();
            String instrutor = instrutorController.buscarPorId(treino.getInstrutorId()).getNome();
            System.out.println("\nTreino #" + treino.getId() + " - " + treino.getDescricao()
                    + " (" + treino.getData() + ")");
            System.out.println("  Aluno: " + aluno + " | Instrutor: " + instrutor);
            System.out.println("  Exercicios:");
            if (treino.getExercicioIds().isEmpty()) {
                System.out.println("    (nenhum)");
            } else {
                for (int exId : treino.getExercicioIds()) {
                    Exercicio ex = exercicioController.buscarPorId(exId);
                    System.out.println("    - " + ex.getNome() + " [" + ex.getCategoria() + "]");
                }
            }
        }
    }

    private void adicionarExercicio() {
        int treinoId = InputUtil.lerInteiro("Id do treino");
        System.out.println("Exercicios disponiveis:");
        exercicioController.listar().forEach(System.out::println);
        int exId = InputUtil.lerInteiro("Id do exercicio (FK)");
        controller.adicionarExercicio(treinoId, exId);
        System.out.println("Exercicio adicionado ao treino.");
    }

    private void removerExercicio() {
        int treinoId = InputUtil.lerInteiro("Id do treino");
        int exId = InputUtil.lerInteiro("Id do exercicio");
        controller.removerExercicio(treinoId, exId);
        System.out.println("Exercicio removido do treino.");
    }

    private void deletar() {
        int id = InputUtil.lerInteiro("Id do treino");
        controller.deletar(id);
        System.out.println("Treino removido.");
    }
}
