package com.academia.controller;

import com.academia.model.Cardio;
import com.academia.model.Exercicio;
import com.academia.model.Musculacao;
import com.academia.model.enums.NivelIntensidade;
import com.academia.repository.ExercicioRepository;
import com.academia.util.exceptions.DadosInvalidosException;

import java.util.List;


public class ExercicioController {

    private final ExercicioRepository repository = new ExercicioRepository();

    public Exercicio cadastrarMusculacao(String nome, NivelIntensidade nivel,
                                         int series, int repeticoes, double cargaKg) {
        validarNome(nome);
        return repository.salvar(new Musculacao(nome, nivel, series, repeticoes, cargaKg));
    }

    public Exercicio cadastrarCardio(String nome, NivelIntensidade nivel, double distanciaKm) {
        validarNome(nome);
        return repository.salvar(new Cardio(nome, nivel, distanciaKm));
    }

    public void alterarIntensidade(int id, NivelIntensidade nivel) {
        Exercicio exercicio = repository.buscarPorId(id);
        exercicio.setIntensidade(nivel);
        repository.atualizar(exercicio);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }

    public Exercicio buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public boolean existe(int id) {
        return repository.existe(id);
    }

    public List<Exercicio> listar() {
        return repository.listarTodos();
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DadosInvalidosException("O nome do exercicio e obrigatorio.");
        }
    }
}
