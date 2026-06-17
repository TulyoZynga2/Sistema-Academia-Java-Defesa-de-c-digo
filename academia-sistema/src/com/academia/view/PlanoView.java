package com.academia.view;

import com.academia.controller.PlanoController;
import com.academia.model.Plano;
import com.academia.util.InputUtil;

import java.util.List;

/** View do CRUD de Planos: monta o menu e trata as interações do usuário. */
public class PlanoView {

    private final PlanoController controller;

    public PlanoView(PlanoController controller) {
        this.controller = controller;
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- PLANOS ---");
            System.out.println("1 - Cadastrar plano mensal");
            System.out.println("2 - Cadastrar plano trimestral");
            System.out.println("3 - Listar planos");
            System.out.println("4 - Alterar valor");
            System.out.println("5 - Deletar plano");
            System.out.println("0 - Voltar");
            opcao = InputUtil.lerInteiro("Opcao");

            try {
                switch (opcao) {
                    case 1 -> cadastrar(true);
                    case 2 -> cadastrar(false);
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

    private void cadastrar(boolean mensal) {
        String nome = InputUtil.lerTexto("Nome do plano");
        double valor = InputUtil.lerDecimal("Valor base (mensal)");
        Plano plano = mensal
                ? controller.cadastrarMensal(nome, valor)
                : controller.cadastrarTrimestral(nome, valor);
        System.out.println("Plano cadastrado: " + plano);
    }

    private void listar() {
        List<Plano> planos = controller.listar();
        if (planos.isEmpty()) {
            System.out.println("Nenhum plano cadastrado.");
            return;
        }
        for (Plano plano : planos) {
            System.out.println(plano);
        }
    }

    private void alterar() {
        int id = InputUtil.lerInteiro("Id do plano");
        double valor = InputUtil.lerDecimal("Novo valor base");
        controller.alterarValor(id, valor);
        System.out.println("Valor atualizado.");
    }

    private void deletar() {
        int id = InputUtil.lerInteiro("Id do plano");
        controller.deletar(id);
        System.out.println("Plano removido.");
    }
}
