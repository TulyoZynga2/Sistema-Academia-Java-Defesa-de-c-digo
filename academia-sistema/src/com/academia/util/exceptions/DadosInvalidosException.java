package com.academia.util.exceptions;

/**
 * Lançada quando dados informados pelo usuário não passam nas validações
 * de negócio (ex.: nome vazio, valor negativo, CPF mal formado).
 */
public class DadosInvalidosException extends RuntimeException {

    public DadosInvalidosException(String mensagem) {
        super(mensagem);
    }
}
