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


public class Main {

    public static void main(String[] args) {
        
        PlanoController planoController = new PlanoController();
        AlunoController alunoController = new AlunoController(planoController);
        InstrutorController instrutorController = new InstrutorController();
        ExercicioController exercicioController = new ExercicioController();
        TreinoController treinoController = new TreinoController(
                alunoController, instrutorController, exercicioController);
        PagamentoController pagamentoController = new PagamentoController(alunoController);

       
        PlanoView planoView = new PlanoView(planoController);
        AlunoView alunoView = new AlunoView(alunoController, planoController);
        InstrutorView instrutorView = new InstrutorView(instrutorController);
        ExercicioView exercicioView = new ExercicioView(exercicioController);
        TreinoView treinoView = new TreinoView(
                treinoController, alunoController, instrutorController, exercicioController);
        PagamentoView pagamentoView = new PagamentoView(pagamentoController, alunoController);

       
        MenuPrincipalView menu = new MenuPrincipalView(
                planoView, alunoView, instrutorView, exercicioView, treinoView, pagamentoView);
        menu.iniciar();
    }
}
