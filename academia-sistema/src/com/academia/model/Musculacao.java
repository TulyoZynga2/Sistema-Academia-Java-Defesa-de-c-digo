package com.academia.model;

import com.academia.model.enums.NivelIntensidade;

/** Exercício de musculação, com séries, repetições e carga. */
public class Musculacao extends Exercicio {

    private static final long serialVersionUID = 1L;

    private int series;
    private int repeticoes;
    private double cargaKg;

    public Musculacao(String nome, NivelIntensidade intensidade,
                      int series, int repeticoes, double cargaKg) {
        super(nome, intensidade);
        this.series = series;
        this.repeticoes = repeticoes;
        this.cargaKg = cargaKg;
    }

    @Override
    public int calcularCaloriasEstimadas(int minutos) {
        // base ~5 cal/min * fator de intensidade
        return (int) Math.round(5 * minutos * getIntensidade().getFatorCalorico());
    }

    @Override
    public String getCategoria() {
        return "Musculacao";
    }

    public int getSeries() { return series; }
    public void setSeries(int series) { this.series = series; }

    public int getRepeticoes() { return repeticoes; }
    public void setRepeticoes(int repeticoes) { this.repeticoes = repeticoes; }

    public double getCargaKg() { return cargaKg; }
    public void setCargaKg(double cargaKg) { this.cargaKg = cargaKg; }

    @Override
    public String toString() {
        return super.toString() + " | " + series + "x" + repeticoes
                + " com " + cargaKg + "kg";
    }
}
