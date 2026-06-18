package com.academia.model;

import com.academia.interfaces.Identificavel;
import com.academia.model.enums.FormaPagamento;
import com.academia.model.enums.StatusPagamento;

import java.io.Serializable;
import java.time.LocalDate;


public class Pagamento implements Identificavel, Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int alunoId;             
    private double valor;
    private LocalDate data;
    private StatusPagamento status;
    private FormaPagamento forma;
    private String referencia;       

    public Pagamento(int alunoId, double valor, LocalDate data,
                     StatusPagamento status, FormaPagamento forma, String referencia) {
        this.alunoId = alunoId;
        this.valor = valor;
        this.data = data;
        this.status = status;
        this.forma = forma;
        this.referencia = referencia;
    }

    @Override
    public int getId() { return id; }

    @Override
    public void setId(int id) { this.id = id; }

    public int getAlunoId() { return alunoId; }
    public void setAlunoId(int alunoId) { this.alunoId = alunoId; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public StatusPagamento getStatus() { return status; }
    public void setStatus(StatusPagamento status) { this.status = status; }

    public FormaPagamento getForma() { return forma; }
    public void setForma(FormaPagamento forma) { this.forma = forma; }

    public String getReferencia() { return referencia; }
    public void setReferencia(String referencia) { this.referencia = referencia; }

    @Override
    public String toString() {
        return "#" + id + " | Aluno FK=" + alunoId + " | R$ "
                + String.format("%.2f", valor) + " | " + status.getDescricao()
                + " | " + forma.getDescricao() + " | " + referencia;
    }
}
