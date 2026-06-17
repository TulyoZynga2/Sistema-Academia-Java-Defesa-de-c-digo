package com.academia.view;

import com.academia.util.InputUtil;
import com.academia.util.LogService;

/**
 * Menu principal do sistema. Orquestra a navegação entre os módulos
 * (CRUDs) e mantém o laço principal de execução.
 */
public class MenuPrincipalView {

    private final PlanoView planoView;
    private final AlunoView alunoView;
    private final InstrutorView instrutorView;
    private final ExercicioView exercicioView;
    private final TreinoView treinoView;
    private final PagamentoView pagamentoView;

    public MenuPrincipalView(PlanoView planoView, AlunoView alunoView,
                             InstrutorView instrutorView, ExercicioView exercicioView,
                             TreinoView treinoView, PagamentoView pagamentoView) {
        this.planoView = planoView;
        this.alunoView = alunoView;
        this.instrutorView = instrutorView;
        this.exercicioView = exercicioView;
        this.treinoView = treinoView;
        this.pagamentoView = pagamentoView;
    }

    public void iniciar() {
        LogService.registrar("SISTEMA", "Aplicacao iniciada.");
        System.out.println("====================================");
        System.out.println("   SISTEMA DE GESTAO DE ACADEMIA");
        System.out.println("====================================");

        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Planos");
            System.out.println("2 - Alunos");
            System.out.println("3 - Instrutores");
            System.out.println("4 - Exercicios");
            System.out.println("5 - Treinos");
            System.out.println("6 - Pagamentos");
            System.out.println("0 - Sair");

            opcao = InputUtil.lerInteiro("Escolha");

            try {
                switch (opcao) {
                    case 1 -> planoView.menu();
                    case 2 -> alunoView.menu();
                    case 3 -> instrutorView.menu();
                    case 4 -> exercicioView.menu();
                    case 5 -> treinoView.menu();
                    case 6 -> pagamentoView.menu();
                    case 0 -> System.out.println("Encerrando...");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (RuntimeException e) {
                // Rede de segurança: qualquer erro não tratado nas views.
                System.out.println(">> Ocorreu um erro: " + e.getMessage());
                LogService.erro("Erro nao tratado no menu principal", e);
            }
        } while (opcao != 0);

        LogService.registrar("SISTEMA", "Aplicacao encerrada.");
        System.out.println("Ate logo!");
    }
}
