package com.academia.model.enums;


public enum StatusPagamento {
    PENDENTE("Pendente"),
    PAGO("Pago"),
    ATRASADO("Atrasado"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
