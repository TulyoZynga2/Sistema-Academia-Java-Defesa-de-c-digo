package com.academia.model;

import com.academia.interfaces.Identificavel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Treino prescrito a um aluno por um instrutor.
 *
 * Demonstra AGREGAÇÃO: o treino se relaciona com Aluno, Instrutor e vários
 * Exercicios que existem independentemente dele. O vínculo é feito por
 * CHAVES ESTRANGEIRAS (ids), e a lista de exercícios usa COLEÇÃO (List).
 */
public class Treino implements Identificavel, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String descricao;
    private LocalDate data;
    private int alunoId;                  // FK -> Aluno
    private int instrutorId;              // FK -> Instrutor
    private List<Integer> exercicioIds;   // FK -> Exercicio (vários)

    public Treino(String descricao, LocalDate data, int alunoId, int instrutorId) {
        this.descricao = descricao;
        this.data = data;
        this.alunoId = alunoId;
        this.instrutorId = instrutorId;
        this.exercicioIds = new ArrayList<>();
    }

    public void adicionarExercicio(int exercicioId) {
        if (!exercicioIds.contains(exercicioId)) {
            exercicioIds.add(exercicioId);
        }
    }

    public void removerExercicio(int exercicioId) {
        exercicioIds.remove(Integer.valueOf(exercicioId));
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }

    public int getInstrutorId() { return instrutorId; }
    public void setInstrutorId(int instrutorId) { this.instrutorId = instrutorId; }

    public List<Integer> getExercicioIds() { return exercicioIds; }
}
