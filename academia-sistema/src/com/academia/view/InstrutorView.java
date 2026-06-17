package com.academia.view;

import com.academia.controller.InstrutorController;
import com.academia.model.Endereco;
import com.academia.model.Instrutor;
import com.academia.util.InputUtil;

import java.util.List;

/** View do CRUD de Instrutores. */
public class InstrutorView {

    private final InstrutorController controller;

    public InstrutorView(InstrutorController controller) {
        this.controller = controller;
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n--- INSTRUTORES ---");
            System.out.println("1 - Cadastrar instrutor");
            System.out.println("2 - Listar instrutores");
            System.out.println("3 - Alterar especialidade");
            System.out.println("4 - Deletar instrutor");
            System.out.println("0 - Voltar");
            opcao = InputUtil.lerInteiro("Opcao");

            try {
                switch (opcao) {
                    case 1 -> cadastrar();
                    case 2 -> listar();
                    case 3 -> alterar();
                    case 4 -> deletar();
                    case 0 -> System.out.println("Voltando...");
                    default -> System.out.println("Opcao invalida.");
                }
            } catch (RuntimeException e) {
                System.out.println(">> Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    private void cadastrar() {
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

        String registro = InputUtil.lerTexto("Registro (CREF)");
        String especialidade = InputUtil.lerTexto("Especialidade");
        double salario = InputUtil.lerDecimal("Salario");

        Instrutor instrutor = controller.cadastrar(nome, cpf, telefone, endereco,
                registro, especialidade, salario);
        System.out.println("Instrutor cadastrado: " + instrutor);
    }

    private void listar() {
        List<Instrutor> instrutores = controller.listar();
        if (instrutores.isEmpty()) {
            System.out.println("Nenhum instrutor cadastrado.");
            return;
        }
        for (Instrutor instrutor : instrutores) {
            System.out.println(instrutor + " | Salario R$ "
                    + String.format("%.2f", instrutor.getSalario()));
        }
    }

    private void alterar() {
        int id = InputUtil.lerInteiro("Id do instrutor");
        String especialidade = InputUtil.lerTexto("Nova especialidade");
        controller.alterarEspecialidade(id, especialidade);
        System.out.println("Especialidade atualizada.");
    }

    private void deletar() {
        int id = InputUtil.lerInteiro("Id do instrutor");
        controller.deletar(id);
        System.out.println("Instrutor removido.");
    }
}
