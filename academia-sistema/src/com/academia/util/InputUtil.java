package com.academia.util;

import com.academia.util.exceptions.DadosInvalidosException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Centraliza a leitura de dados do teclado, com validação e conversão de tipos.
 * Evita repetição de código de Scanner em todas as Views (Clean Code).
 */
public final class InputUtil {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private InputUtil() {
    }

    public static String lerTexto(String rotulo) {
        System.out.print(rotulo + ": ");
        String entrada = SCANNER.nextLine().trim();
        if (entrada.isEmpty()) {
            throw new DadosInvalidosException("O campo '" + rotulo + "' nao pode ficar vazio.");
        }
        return entrada;
    }

    public static int lerInteiro(String rotulo) {
        System.out.print(rotulo + ": ");
        String entrada = SCANNER.nextLine().trim();
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            throw new DadosInvalidosException("'" + entrada + "' nao e um numero inteiro valido.");
        }
    }

    public static double lerDecimal(String rotulo) {
        System.out.print(rotulo + ": ");
        String entrada = SCANNER.nextLine().trim().replace(",", ".");
        try {
            double valor = Double.parseDouble(entrada);
            if (valor < 0) {
                throw new DadosInvalidosException("O valor nao pode ser negativo.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new DadosInvalidosException("'" + entrada + "' nao e um numero valido.");
        }
    }

    public static LocalDate lerData(String rotulo) {
        System.out.print(rotulo + " (dd/MM/aaaa): ");
        String entrada = SCANNER.nextLine().trim();
        try {
            return LocalDate.parse(entrada, FORMATO_DATA);
        } catch (DateTimeParseException e) {
            throw new DadosInvalidosException("Data invalida. Use o formato dd/MM/aaaa.");
        }
    }

    /** Pausa a execução até o usuário pressionar ENTER. */
    public static void pausar() {
        System.out.print("\nPressione ENTER para continuar...");
        SCANNER.nextLine();
    }
}
