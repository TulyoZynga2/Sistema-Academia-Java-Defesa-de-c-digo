package com.academia.model;


public class PlanoTrimestral extends Plano {

    private static final long serialVersionUID = 1L;
    private static final double DESCONTO = 0.10;

    public PlanoTrimestral(String nome, double valorBase) {
        super(nome, valorBase);
    }

    @Override
    public double calcularValorFinal() {
        double total = getValorBase() * getDuracaoMeses();
        return total - (total * DESCONTO);
    }

    @Override
    public int getDuracaoMeses() {
        return 3;
    }

    @Override
    public String getTipoPlano() {
        return "Trimestral";
    }
}
