package com.academia.model;

/** Plano de 1 mês, sem desconto. Implementa os métodos abstratos de Plano. */
public class PlanoMensal extends Plano {

    private static final long serialVersionUID = 1L;

    public PlanoMensal(String nome, double valorBase) {
        super(nome, valorBase);
    }

    @Override
    public double calcularValorFinal() {
        return getValorBase(); // 1 mês, valor cheio
    }

    @Override
    public int getDuracaoMeses() {
        return 1;
    }

    @Override
    public String getTipoPlano() {
        return "Mensal";
    }
}
