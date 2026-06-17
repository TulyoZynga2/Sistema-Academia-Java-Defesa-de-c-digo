package com.academia;

import com.academia.controller.AlunoController;
import com.academia.controller.ExercicioController;
import com.academia.controller.InstrutorController;
import com.academia.controller.PagamentoController;
import com.academia.controller.PlanoController;
import com.academia.controller.TreinoController;
import com.academia.view.AlunoView;
import com.academia.view.ExercicioView;
import com.academia.view.InstrutorView;
import com.academia.view.MenuPrincipalView;
import com.academia.view.PagamentoView;
import com.academia.view.PlanoView;
import com.academia.view.TreinoView;

/**
 * Ponto de entrada do sistema.
 *
 * Aqui as camadas do MVC são montadas e conectadas (injeção de dependência
 * manual): os Controllers recebem uns aos outros para validar as chaves
 * estrangeiras, e as Views recebem seus Controllers. Os dados são carregados
 * automaticamente do disco quando cada Repositorio é instanciado.
 */
public class Main {

    public static void main(String[] args) {
        // ---- Controllers (camada de regra de negócio) ----
        PlanoController planoController = new PlanoController();
        AlunoController alunoController = new AlunoController(planoController);
        InstrutorController instrutorController = new InstrutorController();
        ExercicioController exercicioController = new ExercicioController();
        TreinoController treinoController = new TreinoController(
                alunoController, instrutorController, exercicioController);
        PagamentoController pagamentoController = new PagamentoController(alunoController);

        // ---- Views (camada de interface) ----
        PlanoView planoView = new PlanoView(planoController);
        AlunoView alunoView = new AlunoView(alunoController, planoController);
        InstrutorView instrutorView = new InstrutorView(instrutorController);
        ExercicioView exercicioView = new ExercicioView(exercicioController);
        TreinoView treinoView = new TreinoView(
                treinoController, alunoController, instrutorController, exercicioController);
        PagamentoView pagamentoView = new PagamentoView(pagamentoController, alunoController);

        // ---- Menu principal ----
        MenuPrincipalView menu = new MenuPrincipalView(
                planoView, alunoView, instrutorView, exercicioView, treinoView, pagamentoView);
        menu.iniciar();
    }
}
