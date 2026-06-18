package com.academia.model;


public class Instrutor extends Pessoa {

    private static final long serialVersionUID = 1L;

    private String registroProfissional; 
    private String especialidade;
    private double salario;

    public Instrutor(String nome, String cpf, String telefone, Endereco endereco,
                     String registroProfissional, String especialidade, double salario) {
        super(nome, cpf, telefone, endereco);
        this.registroProfissional = registroProfissional;
        this.especialidade = especialidade;
        this.salario = salario;
    }

    @Override
    public String getTipo() {
        return "Instrutor";
    }

    @Override
    public String getResumo() {
        return super.getResumo() + " - " + especialidade;
    }

    public String getRegistroProfissional() { return registroProfissional; }
    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}
