package com.academia.model;

import com.academia.model.enums.NivelIntensidade;


public class Cardio extends Exercicio {

    private static final long serialVersionUID = 1L;

    private double distanciaKm;

    public Cardio(String nome, NivelIntensidade intensidade, double distanciaKm) {
        super(nome, intensidade);
        this.distanciaKm = distanciaKm;
    }

    @Override
    public int calcularCaloriasEstimadas(int minutos) {
        
        return (int) Math.round(8 * minutos * getIntensidade().getFatorCalorico());
    }

    @Override
    public String getCategoria() {
        return "Cardio";
    }

    public double getDistanciaKm() { return distanciaKm; }
    public void setDistanciaKm(double distanciaKm) { this.distanciaKm = distanciaKm; }

    @Override
    public String toString() {
        return super.toString() + " | " + distanciaKm + "km";
    }
}
