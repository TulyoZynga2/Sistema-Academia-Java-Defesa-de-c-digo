package com.academia.model;

import com.academia.interfaces.Pagavel;

import java.time.LocalDate;


public class Aluno extends Pessoa implements Pagavel {

    private static final long serialVersionUID = 1L;

    private String matricula;
    private int planoId;             
    private double mensalidadeBase;  
    private LocalDate dataMatricula;
    private boolean ativo;

    public Aluno(String nome, String cpf, String telefone, Endereco endereco,
                 String matricula, int planoId, double mensalidadeBase,
                 LocalDate dataMatricula) {
        super(nome, cpf, telefone, endereco);
        this.matricula = matricula;
        this.planoId = planoId;
        this.mensalidadeBase = mensalidadeBase;
        this.dataMatricula = dataMatricula;
        this.ativo = true;
    }

    

    @Override
    public String getTipo() {
        return "Aluno";
    }

    @Override
    public String getResumo() {
        return super.getResumo() + " (Matricula " + matricula + ")";
    }

   

    @Override
    public double calcularMensalidade() {
        return mensalidadeBase;
    }

    @Override
    public double calcularMensalidade(int meses) {
        return mensalidadeBase * meses;
    }

    @Override
    public double calcularMensalidade(int meses, double percentualDesconto) {
        double total = mensalidadeBase * meses;
        return total - (total * percentualDesconto / 100.0);
    }

    

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public int getPlanoId() { return planoId; }
    public void setPlanoId(int planoId) { this.planoId = planoId; }

    public double getMensalidadeBase() { return mensalidadeBase; }
    public void setMensalidadeBase(double mensalidadeBase) { this.mensalidadeBase = mensalidadeBase; }

    public LocalDate getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(LocalDate dataMatricula) { this.dataMatricula = dataMatricula; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
