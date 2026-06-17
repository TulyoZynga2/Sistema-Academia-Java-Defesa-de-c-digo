package com.academia.view;

import com.academia.controller.AlunoController;
import com.academia.controller.PlanoController;
import com.academia.model.Aluno;
import com.academia.model.Endereco;
import com.academia.util.InputUtil;

import java.time.LocalDate;
import java.util.List;

/** View do CRUD de Alunos. */
public class AlunoView {

    private final AlunoController controller;
    private final PlanoController planoController;

    public AlunoView(AlunoController controller, PlanoController planoController) {
        this.controller = controller;
        this.planoController = planoController;
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- ALUNOS ---");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Listar alunos");
            System.out.println("3 - Alterar telefone");
            System.out.println("4 - Inativar aluno");
            System.out.println("5 - Deletar aluno");
            System.out.println("0 - Voltar");
            opcao = InputUtil.lerInteiro("Opcao");

            try {
                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> alterarTelefone();
                    case 4 -> inativar();
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
        System.out.println("Planos disponiveis:");
        planoController.listar().forEach(System.out::println);
        if (planoController.listar().isEmpty()) {
            System.out.println("Cadastre um plano antes de matricular um aluno.");
            return;
        }

        String nome = InputUtil.lerTexto("Nome");
        String cpf = InputUtil.lerTexto("CPF");
        String telefone = InputUtil.lerTexto("Telefone");

        System.out.println("-- Endereco --");
        String rua = InputUtil.lerTexto("Rua");
        String numero = InputUtil.lerTexto("Numero");
        String bairro = InputUtil.lerTexto("Bairro");
        String cidade = InputUtil.lerTexto("Cidade");
        String cep = InputUtil.lerTexto("CEP");
        Endereco endereco = new Endereco(rua, numero, bairro, cidade, cep);

        String matricula = InputUtil.lerTexto("Matricula");
        int planoId = InputUtil.lerInteiro("Id do plano (FK)");
        LocalDate data = InputUtil.lerData("Data da matricula");

        Aluno aluno = controller.cadastrar(nome, cpf, telefone, endereco,
                matricula, planoId, data);
        System.out.println("Aluno cadastrado: " + aluno);
    }

    private void listar() {
        List<Aluno> alunos = controller.listar();
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }
        for (Aluno aluno : alunos) {
            String situacao = aluno.isAtivo() ? "ATIVO" : "INATIVO";
            System.out.println(aluno + " | Plano FK=" + aluno.getPlanoId()
                    + " | Mensalidade R$ " + String.format("%.2f", aluno.calcularMensalidade())
                    + " | " + situacao);
        }
    }

    private void alterarTelefone() {
        int id = InputUtil.lerInteiro("Id do aluno");
        String telefone = InputUtil.lerTexto("Novo telefone");
        controller.alterarTelefone(id, telefone);
        System.out.println("Telefone atualizado.");
    }

    private void inativar() {
        int id = InputUtil.lerInteiro("Id do aluno");
        controller.inativar(id);
        System.out.println("Aluno inativado.");
    }

    private void deletar() {
        int id = InputUtil.lerInteiro("Id do aluno");
        controller.deletar(id);
        System.out.println("Aluno removido.");
    }
}
