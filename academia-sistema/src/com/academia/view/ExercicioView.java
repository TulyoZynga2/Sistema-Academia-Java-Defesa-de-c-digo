package com.academia.view;

import com.academia.controller.ExercicioController;
import com.academia.model.Exercicio;
import com.academia.model.enums.NivelIntensidade;
import com.academia.util.InputUtil;

import java.util.List;

/** View do CRUD de Exercicios. */
public class ExercicioView {

    private final ExercicioController controller;

    public ExercicioView(ExercicioController controller) {
        this.controller = controller;
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- EXERCICIOS ---");
            System.out.println("1 - Cadastrar musculacao");
            System.out.println("2 - Cadastrar cardio");
            System.out.println("3 - Listar exercicios");
            System.out.println("4 - Alterar intensidade");
            System.out.println("5 - Deletar exercicio");
            System.out.println("0 - Voltar");
            opcao = InputUtil.lerInteiro("Opcao");

            try {
                switch (opcao) {
                    case 1 -> cadastrarMusculacao();
                    case 2 -> cadastrarCardio();
                    case 3 -> listar();
                    case 4 -> alterar();
                    case 5 -> deletar();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (RuntimeException e) {
                System.out.println(">> Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private NivelIntensidade lerIntensidade() {
        System.out.println("Intensidade: 1-BAIXA  2-MODERADA  3-ALTA");
        int n = InputUtil.lerInteiro("Opcao");
        return switch (n) {
            case 1 -> NivelIntensidade.BAIXA;
            case 2 -> NivelIntensidade.MODERADA;
            case 3 -> NivelIntensidade.ALTA;
            default -> NivelIntensidade.MODERADA;
        };
    }

    private void cadastrarMusculacao() {
        String nome = InputUtil.lerTexto("Nome");
        NivelIntensidade nivel = lerIntensidade();
        int series = InputUtil.lerInteiro("Series");
        int reps = InputUtil.lerInteiro("Repeticoes");
        double carga = InputUtil.lerDecimal("Carga (kg)");
        Exercicio ex = controller.cadastrarMusculacao(nome, nivel, series, reps, carga);
        System.out.println("Cadastrado: " + ex);
    }

    private void cadastrarCardio() {
        String nome = InputUtil.lerTexto("Nome");
        NivelIntensidade nivel = lerIntensidade();
        double distancia = InputUtil.lerDecimal("Distancia (km)");
        Exercicio ex = controller.cadastrarCardio(nome, nivel, distancia);
        System.out.println("Cadastrado: " + ex);
    }

    private void listar() {
        List<Exercicio> exercicios = controller.listar();
        if (exercicios.isEmpty()) {
            System.out.println("Nenhum exercicio cadastrado.");
            return;
        }
        // Polimorfismo: cada exercicio calcula calorias à sua maneira.
        for (Exercicio ex : exercicios) {
            System.out.println(ex + " | ~" + ex.calcularCaloriasEstimadas(30)
                    + " kcal em 30 min");
        }
    }

    private void alterar() {
        int id = InputUtil.lerInteiro("Id do exercicio");
        NivelIntensidade nivel = lerIntensidade();
        controller.alterarIntensidade(id, nivel);
        System.out.println("Intensidade atualizada.");
    }

    private void deletar() {
        int id = InputUtil.lerInteiro("Id do exercicio");
        controller.deletar(id);
        System.out.println("Exercicio removido.");
    }
}
