package com.academia.util.exceptions;

/**
 * Lançada quando um registro buscado por id não existe no repositório.
 * Também é usada para validar integridade de chave estrangeira.
 */
public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
