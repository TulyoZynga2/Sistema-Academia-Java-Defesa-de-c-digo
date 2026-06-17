package com.academia.view;

import com.academia.controller.AlunoController;
import com.academia.controller.PagamentoController;
import com.academia.model.Pagamento;
import com.academia.model.enums.FormaPagamento;
import com.academia.model.enums.StatusPagamento;
import com.academia.util.InputUtil;

import java.time.LocalDate;
import java.util.List;

/** View do CRUD de Pagamentos. */
public class PagamentoView {

    private final PagamentoController controller;
    private final AlunoController alunoController;

    public PagamentoView(PagamentoController controller, AlunoController alunoController) {
        this.controller = controller;
        this.alunoController = alunoController;
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- PAGAMENTOS ---");
            System.out.println("1 - Registrar pagamento");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Listar por aluno");
            System.out.println("4 - Alterar status");
            System.out.println("5 - Deletar pagamento");
            System.out.println("0 - Voltar");
            opcao = InputUtil.lerInteiro("Opcao");

            try {
                switch (opcao) {
                    case 1 -> registrar();
                    case 2 -> listar(controller.listar());
                    case 3 -> listarPorAluno();
                    case 4 -> alterarStatus();
                    case 5 -> deletar();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (RuntimeException e) {
                System.out.println(">> Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private FormaPagamento lerForma() {
        System.out.println("Forma: 1-DINHEIRO 2-CREDITO 3-DEBITO 4-PIX 5-BOLETO");
        int n = InputUtil.lerInteiro("Opcao");
        return switch (n) {
            case 1 -> FormaPagamento.DINHEIRO;
            case 2 -> FormaPagamento.CARTAO_CREDITO;
            case 3 -> FormaPagamento.CARTAO_DEBITO;
            case 4 -> FormaPagamento.PIX;
            case 5 -> FormaPagamento.BOLETO;
            default -> FormaPagamento.PIX;
        };
    }

    private StatusPagamento lerStatus() {
        System.out.println("Status: 1-PENDENTE 2-PAGO 3-ATRASADO 4-CANCELADO");
        int n = InputUtil.lerInteiro("Opcao");
        return switch (n) {
            case 1 -> StatusPagamento.PENDENTE;
            case 2 -> StatusPagamento.PAGO;
            case 3 -> StatusPagamento.ATRASADO;
            case 4 -> StatusPagamento.CANCELADO;
            default -> StatusPagamento.PENDENTE;
        };
    }

    private void registrar() {
        int alunoId = InputUtil.lerInteiro("Id do aluno (FK)");
        System.out.println("Informe o valor manualmente ou digite 0 para calcular pelo plano.");
        double valor = InputUtil.lerDecimal("Valor (0 = automatico)");
        int meses = InputUtil.lerInteiro("Qtd de meses (1 = mensalidade unica)");
        LocalDate data = InputUtil.lerData("Data do pagamento");
        FormaPagamento forma = lerForma();
        StatusPagamento status = lerStatus();
        String referencia = InputUtil.lerTexto("Referencia (ex.: Mensalidade 06/2026)");

        Pagamento pagamento = controller.registrar(alunoId, valor, meses, data,
                forma, status, referencia);
        System.out.println("Pagamento registrado: " + pagamento);
    }

    private void listar(List<Pagamento> pagamentos) {
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento encontrado.");
            return;
        }
        for (Pagamento pagamento : pagamentos) {
            String nomeAluno = alunoController.buscarPorId(pagamento.getAlunoId()).getNome();
            System.out.println(pagamento + " | Aluno: " + nomeAluno);
        }
    }

    private void listarPorAluno() {
        int alunoId = InputUtil.lerInteiro("Id do aluno");
        listar(controller.listarPorAluno(alunoId));
    }

    private void alterarStatus() {
        int id = InputUtil.lerInteiro("Id do pagamento");
        StatusPagamento status = lerStatus();
        controller.alterarStatus(id, status);
        System.out.println("Status atualizado.");
    }

    private void deletar() {
        int id = InputUtil.lerInteiro("Id do pagamento");
        controller.deletar(id);
        System.out.println("Pagamento removido.");
    }
}
