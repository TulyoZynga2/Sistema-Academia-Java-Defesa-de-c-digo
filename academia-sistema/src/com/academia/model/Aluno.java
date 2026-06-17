package com.academia.model;

import com.academia.interfaces.Pagavel;

import java.time.LocalDate;

/**
 * Aluno HERDA de Pessoa e IMPLEMENTA a interface Pagavel.
 *
 * - POLIMORFISMO DE SOBRESCRITA: redefine getTipo() e getResumo().
 * - POLIMORFISMO DE SOBRECARGA: três versões de calcularMensalidade().
 * - CHAVE ESTRANGEIRA: guarda o id do Plano (planoId) ao qual está associado.
 *
 * Observação de modelagem: o Aluno guarda apenas o id do plano (FK) e uma
 * "foto" do valor da mensalidade no momento da matrícula. Assim a camada de
 * modelo não depende dos repositórios, mantendo o MVC desacoplado.
 */
public class Aluno extends Pessoa implements Pagavel {

    private static final long serialVersionUID = 1L;

    private String matricula;
    private int planoId;             // chave estrangeira -> Plano
    private double mensalidadeBase;  // valor do plano no momento da matrícula
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

    // ---- Polimorfismo de SOBRESCRITA -------------------------------------

    @Override
    public String getTipo() {
        return "Aluno";
    }

    @Override
    public String getResumo() {
        return super.getResumo() + " (Matricula " + matricula + ")";
    }

    // ---- Polimorfismo de SOBRECARGA (contrato Pagavel) -------------------

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

    // ---- Getters / Setters ----------------------------------------------

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
