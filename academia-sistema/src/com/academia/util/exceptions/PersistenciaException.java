package com.academia.util.exceptions;

/**
 * Encapsula erros de leitura/gravação de arquivos (I/O) ocorridos
 * durante a serialização ou desserialização dos dados.
 */
public class PersistenciaException extends RuntimeException {

    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
