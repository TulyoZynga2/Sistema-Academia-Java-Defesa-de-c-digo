package com.academia.model.enums;

/** Nível de intensidade de um exercício, usado no cálculo de calorias. */
public enum NivelIntensidade {
    BAIXA("Baixa", 1.0),
    MODERADA("Moderada", 1.5),
    ALTA("Alta", 2.2);

    private final String descricao;
    private final double fatorCalorico;

    NivelIntensidade(String descricao, double fatorCalorico) {
        this.descricao = descricao;
        this.fatorCalorico = fatorCalorico;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getFatorCalorico() {
        return fatorCalorico;
    }
}
