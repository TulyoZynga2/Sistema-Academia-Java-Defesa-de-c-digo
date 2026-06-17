package com.academia.model;

import com.academia.interfaces.Identificavel;

import java.io.Serializable;

/**
 * Classe ABSTRATA que representa um plano de matrícula.
 * O método abstrato calcularValorFinal() é implementado de forma
 * diferente em cada subclasse (polimorfismo de sobrescrita).
 */
public abstract class Plano implements Identificavel, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private double valorBase; // valor de uma mensalidade

    protected Plano(String nome, double valorBase) {
        this.nome = nome;
        this.valorBase = valorBase;
    }

    /** Valor total a pagar pelo plano (com ou sem desconto, conforme a filha). */
    public abstract double calcularValorFinal();

    /** Quantidade de meses de duração do plano. */
    public abstract int getDuracaoMeses();

    /** Nome do tipo de plano (Mensal/Trimestral...). */
    public abstract String getTipoPlano();

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValorBase() { return valorBase; }
    public void setValorBase(double valorBase) { this.valorBase = valorBase; }

    @Override
    public String toString() {
        return "#" + id + " " + nome + " [" + getTipoPlano() + "] - "
                + getDuracaoMeses() + " mes(es) | Total: R$ "
                + String.format("%.2f", calcularValorFinal());
    }
}
