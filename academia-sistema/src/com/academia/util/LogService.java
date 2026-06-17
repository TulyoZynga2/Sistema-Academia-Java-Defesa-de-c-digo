package com.academia.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Serviço responsável por registrar as operações do sistema em um arquivo
 * de log (logs/sistema.log). Atende ao requisito de LOG em txt.
 *
 * Todos os métodos são estáticos: o log é um recurso global e único,
 * acessado por repositórios, controllers e tratadores de exceção.
 */
public final class LogService {

    private static final String PASTA = "logs";
    private static final String ARQUIVO = PASTA + "/sistema.log";
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Construtor privado: classe utilitária não deve ser instanciada.
    private LogService() {
    }

    /** Registra uma atividade comum (cadastro, alteração, deleção...). */
    public static void registrar(String tipo, String mensagem) {
        escrever("[" + tipo + "] " + mensagem);
    }

    /** Registra um erro junto da mensagem da exceção que o causou. */
    public static void erro(String mensagem, Throwable causa) {
        escrever("[ERRO] " + mensagem + " | Causa: " + causa.getMessage());
    }

    private static void escrever(String conteudo) {
        try {
            Files.createDirectories(Path.of(PASTA));
            // try-with-resources garante o fechamento do arquivo (boa prática).
            try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO, true))) {
                writer.println(LocalDateTime.now().format(FORMATO) + " - " + conteudo);
            }
        } catch (IOException e) {
            // Falha de log não pode derrubar o sistema; apenas avisa no console.
            System.err.println("Nao foi possivel gravar no log: " + e.getMessage());
        }
    }
}
