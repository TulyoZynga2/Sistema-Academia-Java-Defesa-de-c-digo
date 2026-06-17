package com.academia.controller;

import com.academia.model.Plano;
import com.academia.model.PlanoMensal;
import com.academia.model.PlanoTrimestral;
import com.academia.repository.PlanoRepository;
import com.academia.util.exceptions.DadosInvalidosException;

import java.util.List;

/**
 * Controller do CRUD de Planos. Faz a ponte entre View e Repository,
 * aplicando validações de negócio antes de persistir.
 */
public class PlanoController {

    private final PlanoRepository repository = new PlanoRepository();

    public Plano cadastrarMensal(String nome, double valorBase) {
        validar(nome, valorBase);
        return repository.salvar(new PlanoMensal(nome, valorBase));
    }

    public Plano cadastrarTrimestral(String nome, double valorBase) {
        validar(nome, valorBase);
        return repository.salvar(new PlanoTrimestral(nome, valorBase));
    }

    public void alterarValor(int id, double novoValor) {
        if (novoValor <= 0) {
            throw new DadosInvalidosException("O valor do plano deve ser maior que zero.");
        }
        Plano plano = repository.buscarPorId(id);
        plano.setValorBase(novoValor);
        repository.atualizar(plano);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }

    public Plano buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Plano> listar() {
        return repository.listarTodos();
    }

    private void validar(String nome, double valorBase) {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("O nome do plano e obrigatorio.");
        }
        if (valorBase <= 0) {
            throw new DadosInvalidosException("O valor base deve ser maior que zero.");
        }
    }
}
