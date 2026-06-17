package com.academia.repository;

import com.academia.model.Pagamento;
import java.util.ArrayList;
import java.util.List;

public class PagamentoRepository extends Repositorio<Pagamento> {

    public PagamentoRepository() {
        super("pagamentos.dat");
    }

    /** Lista todos os pagamentos de um aluno (uso da chave estrangeira). */
    public List<Pagamento> listarPorAluno(int alunoId) {
        List<Pagamento> resultado = new ArrayList<>();
        for (Pagamento pagamento : registros) {
            if (pagamento.getAlunoId() == alunoId) {
                resultado.add(pagamento);
            }
        }
        return resultado;
    }
}
