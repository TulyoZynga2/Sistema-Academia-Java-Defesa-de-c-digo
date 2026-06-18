package com.academia.model;

import com.academia.interfaces.Identificavel;
import com.academia.model.enums.NivelIntensidade;

import java.io.Serializable;


public abstract class Exercicio implements Identificavel, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private NivelIntensidade intensidade;

    protected Exercicio(String nome, NivelIntensidade intensidade) {
        this.nome = nome;
        this.intensidade = intensidade;
    }

    
    public abstract int calcularCaloriasEstimadas(int minutos);

  
    public abstract String getCategoria();

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public NivelIntensidade getIntensidade() { return intensidade; }
    public void setIntensidade(NivelIntensidade intensidade) { this.intensidade = intensidade; }

    @Override
    public String toString() {
        return "#" + id + " " + nome + " [" + getCategoria()
                + "] - Intensidade " + intensidade.getDescricao();
    }
}
