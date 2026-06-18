package com.academia.model;


public class PlanoMensal extends Plano {

    private static final long serialVersionUID = 1L;

    public PlanoMensal(String nome, double valorBase) {
        super(nome, valorBase);
    }

    @Override
    public double calcularValorFinal() {
        return getValorBase(); 
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
