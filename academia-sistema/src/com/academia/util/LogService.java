package com.academia.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public final class LogService {

    private static final String PASTA = "logs";
    private static final String ARQUIVO = PASTA + "/sistema.log";
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    
    private LogService() {
    }

    
    public static void registrar(String tipo, String mensagem) {
        escrever("[" + tipo + "] " + mensagem);
    }

    
    public static void erro(String mensagem, Throwable causa) {
        escrever("[ERRO] " + mensagem + " | Causa: " + causa.getMessage());
    }

    private static void escrever(String conteudo) {
        try {
            Files.createDirectories(Path.of(PASTA));
           
            try (PrintWriter writer = new PrintWriter(new FileWriter(ARQUIVO, true))) {
                writer.println(LocalDateTime.now().format(FORMATO) + " - " + conteudo);
            }
        } catch (IOException e) {
          
            System.err.println("Nao foi possivel gravar no log: " + e.getMessage());
        }
    }
}
