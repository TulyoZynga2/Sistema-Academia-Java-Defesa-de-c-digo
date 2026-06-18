package com.academia.controller;

import com.academia.model.Aluno;
import com.academia.model.Endereco;
import com.academia.model.Plano;
import com.academia.repository.AlunoRepository;
import com.academia.util.exceptions.DadosInvalidosException;
import com.academia.util.exceptions.EntidadeNaoEncontradaException;

import java.time.LocalDate;
import java.util.List;


public class AlunoController {

    private final AlunoRepository repository = new AlunoRepository();
    private final PlanoController planoController;

    public AlunoController(PlanoController planoController) {
        this.planoController = planoController;
    }

    public Aluno cadastrar(String nome, String cpf, String telefone, Endereco endereco,
                           String matricula, int planoId, LocalDate dataMatricula) {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("O nome do aluno e obrigatorio.");
        }
        if (repository.existeCpf(cpf)) {
            throw new DadosInvalidosException("Ja existe um aluno com o CPF " + cpf + ".");
        }
     
        Plano plano = planoController.buscarPorId(planoId);

        Aluno aluno = new Aluno(nome, cpf, telefone, endereco, matricula,
                plano.getId(), plano.getValorBase(), dataMatricula);
        return repository.salvar(aluno);
    }

    public void alterarTelefone(int id, String telefone) {
        Aluno aluno = repository.buscarPorId(id);
        aluno.setTelefone(telefone);
        repository.atualizar(aluno);
    }

    public void inativar(int id) {
        Aluno aluno = repository.buscarPorId(id);
        aluno.setAtivo(false);
        repository.atualizar(aluno);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }

    public Aluno buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public boolean existe(int id) {
        return repository.existe(id);
    }

    public List<Aluno> listar() {
        return repository.listarTodos();
    }

    
    public Aluno exigirAluno(int id) {
        if (!repository.existe(id)) {
            throw new EntidadeNaoEncontradaException(
                    "Aluno " + id + " nao existe. Cadastre o aluno antes.");
        }
        return repository.buscarPorId(id);
    }
}
