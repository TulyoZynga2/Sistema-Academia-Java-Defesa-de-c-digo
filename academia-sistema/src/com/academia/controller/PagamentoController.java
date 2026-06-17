package com.academia.controller;

import com.academia.model.Aluno;
import com.academia.model.Pagamento;
import com.academia.model.enums.FormaPagamento;
import com.academia.model.enums.StatusPagamento;
import com.academia.repository.PagamentoRepository;
import com.academia.util.exceptions.DadosInvalidosException;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller do CRUD de Pagamentos. Demonstra o relacionamento entre CRUDs:
 * todo pagamento exige um Aluno válido (FK) e usa o cálculo de mensalidade
 * do próprio aluno (interface Pagavel, com sobrecarga).
 */
public class PagamentoController {

    private final PagamentoRepository repository = new PagamentoRepository();
    private final AlunoController alunoController;

    public PagamentoController(AlunoController alunoController) {
        this.alunoController = alunoController;
    }

    /**
     * Registra um pagamento. Se o valor informado for 0, o sistema calcula
     * automaticamente a partir do plano do aluno usando o número de meses
     * (polimorfismo de SOBRECARGA de calcularMensalidade).
     */
    public Pagamento registrar(int alunoId, double valorInformado, int meses,
                               LocalDate data, FormaPagamento forma,
                               StatusPagamento status, String referencia) {
        Aluno aluno = alunoController.exigirAluno(alunoId); // valida FK

        double valor;
        if (valorInformado > 0) {
            valor = valorInformado;
        } else if (meses > 1) {
            valor = aluno.calcularMensalidade(meses);      // sobrecarga (2 args)
        } else {
            valor = aluno.calcularMensalidade();           // sobrecarga (sem args)
        }

        if (valor <= 0) {
            throw new DadosInvalidosException("Valor do pagamento invalido.");
        }

        Pagamento pagamento = new Pagamento(alunoId, valor, data, status, forma, referencia);
        return repository.salvar(pagamento);
    }

    public void alterarStatus(int id, StatusPagamento status) {
        Pagamento pagamento = repository.buscarPorId(id);
        pagamento.setStatus(status);
        repository.atualizar(pagamento);
    }

    public void deletar(int id) {
        repository.deletar(id);
    }

    public Pagamento buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Pagamento> listar() {
        return repository.listarTodos();
    }

    public List<Pagamento> listarPorAluno(int alunoId) {
        return repository.listarPorAluno(alunoId);
    }
}
