package com.academia.interfaces;

/**
 * Contrato para entidades que geram cobrança na academia.
 * As três assinaturas demonstram POLIMORFISMO DE SOBRECARGA (overload):
 * mesmo nome de método, listas de parâmetros diferentes.
 */
public interface Pagavel {

    /** Valor de uma única mensalidade. */
    double calcularMensalidade();

    /** Valor acumulado para uma quantidade de meses. */
    double calcularMensalidade(int meses);

    /** Valor acumulado aplicando um percentual de desconto (0 a 100). */
    double calcularMensalidade(int meses, double percentualDesconto);
}
