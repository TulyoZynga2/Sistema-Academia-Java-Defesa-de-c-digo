package com.academia.interfaces;

/**
 * Contrato que obriga toda entidade persistível a ter um identificador único.
 * É usado pelo {@code Repositorio} genérico para localizar, atualizar e
 * deletar registros sem precisar conhecer o tipo concreto.
 */
public interface Identificavel {

    int getId();

    void setId(int id);
}
