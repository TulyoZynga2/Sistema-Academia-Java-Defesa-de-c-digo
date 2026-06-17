package com.academia.model;

import com.academia.interfaces.Identificavel;

import java.io.Serializable;

/**
 * Superclasse ABSTRATA de Aluno e Instrutor.
 *
 * Demonstra:
 *  - HERANÇA: reúne atributos/métodos comuns às pessoas da academia.
 *  - CLASSE e MÉTODO ABSTRATOS: getTipo() é abstrato e obrigatório nas filhas.
 *  - ENCAPSULAMENTO: atributos privados, acesso por getters/setters.
 *  - COMPOSIÇÃO: contém um Endereco.
 *  - SERIALIZAÇÃO: implementa Serializable para ser gravada em arquivo.
 */
public abstract class Pessoa implements Identificavel, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private Endereco endereco;

    protected Pessoa(String nome, String cpf, String telefone, Endereco endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    /**
     * Método ABSTRATO: cada subclasse informa seu tipo ("Aluno"/"Instrutor").
     * É a base para o polimorfismo de sobrescrita.
     */
    public abstract String getTipo();

    /**
     * Método concreto que usa o método abstrato. Pode ser sobrescrito
     * pelas filhas para acrescentar detalhes (polimorfismo de sobrescrita).
     */
    public String getResumo() {
        return getTipo() + " #" + id + " - " + nome;
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    @Override
    public String toString() {
        return getResumo() + " | CPF: " + cpf + " | Tel: " + telefone;
    }
}
