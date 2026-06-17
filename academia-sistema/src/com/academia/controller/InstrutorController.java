package com.academia.controller;

import com.academia.model.Endereco;
import com.academia.model.Instrutor;
import com.academia.repository.InstrutorRepository;
import com.academia.util.exceptions.DadosInvalidosException;
import com.academia.util.exceptions.EntidadeNaoEncontradaException;

import java.util.List;

/** Controller do CRUD de Instrutores. */
public class InstrutorController {

    private final InstrutorRepository repository = new InstrutorRepository();

    public Instrutor cadastrar(String nome, String cpf, String telefone, Endereco endereco,
                               String registro, String especialidade, double salario) {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("O nome do instrutor e obrigatorio.");
        }
        if (salario < 0) {
            throw new DadosInvalidosException("O salario nao pode ser negativo.");
        }
        Instrutor instrutor = new Instrutor(nome, cpf, telefone, endereco,
                registro, especialidade, salario);
        return repository.salvar(instrutor);
    }

    public void alterarEspecialidade(int id, String especialidade) {
        Instrutor instrutor = repository.buscarPorId(id);
        instrutor.setEspecialidade(especialidade);
        repository.atualizar(instrutor);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }

    public Instrutor buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public boolean existe(int id) {
        return repository.existe(id);
    }

    public List<Instrutor> listar() {
        return repository.listarTodos();
    }

    public Instrutor exigirInstrutor(int id) {
        if (!repository.existe(id)) {
            throw new EntidadeNaoEncontradaException(
                    "Instrutor " + id + " nao existe.");
        }
        return repository.buscarPorId(id);
    }
}
