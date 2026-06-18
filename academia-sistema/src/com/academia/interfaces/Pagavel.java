package com.academia.interfaces;


public interface Pagavel {

    
    double calcularMensalidade();

   
    double calcularMensalidade(int meses);

    
    double calcularMensalidade(int meses, double percentualDesconto);
}
