package com.academia.model;

import com.academia.interfaces.Identificavel;

import java.io.Serializable;


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

  
    public abstract String getTipo();

  
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
