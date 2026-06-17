package com.academia.repository;

import com.academia.interfaces.Identificavel;
import com.academia.util.LogService;
import com.academia.util.exceptions.EntidadeNaoEncontradaException;
import com.academia.util.exceptions.PersistenciaException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório genérico que centraliza o comportamento de CRUD e a
 * PERSISTÊNCIA via SERIALIZAÇÃO de objetos em arquivo (.dat).
 *
 * O uso de Generics (<T extends Identificavel & Serializable>) evita
 * repetir o mesmo código em cada repositório (Clean Code / reutilização).
 *
 * Atende ao requisito do enunciado: "a luz da barraquinha pode acabar e
 * voltar" — os dados são gravados em disco a cada operação, então nada se
 * perde ao reiniciar o sistema.
 */
public abstract class Repositorio<T extends Identificavel & Serializable> {

    private static final String PASTA_DADOS = "dados";

    private final String caminhoArquivo;
    protected List<T> registros;
    private int proximoId;

    protected Repositorio(String nomeArquivo) {
        this.caminhoArquivo = PASTA_DADOS + File.separator + nomeArquivo;
        this.registros = new ArrayList<>();
        this.proximoId = 1;
        garantirPasta();
        carregar();
    }

    // ---------- Operações de CRUD ----------------------------------------

    /** CREATE: atribui id automático, adiciona à coleção e persiste. */
    public T salvar(T entidade) {
        entidade.setId(proximoId++);
        registros.add(entidade);
        persistir();
        LogService.registrar("CADASTRO",
                entidade.getClass().getSimpleName() + " id=" + entidade.getId());
        return entidade;
    }

    /** UPDATE: substitui o registro de mesmo id. */
    public void atualizar(T entidade) {
        int indice = localizarIndice(entidade.getId());
        registros.set(indice, entidade);
        persistir();
        LogService.registrar("ALTERACAO",
                entidade.getClass().getSimpleName() + " id=" + entidade.getId());
    }

    /** DELETE: remove o registro de id informado. */
    public void deletar(int id) {
        T removido = buscarPorId(id);
        registros.remove(removido);
        persistir();
        LogService.registrar("DELECAO",
                removido.getClass().getSimpleName() + " id=" + id);
    }

    /** READ (por id): lança exceção se não existir. */
    public T buscarPorId(int id) {
        for (T registro : registros) {
            if (registro.getId() == id) {
                return registro;
            }
        }
        throw new EntidadeNaoEncontradaException(
                "Registro com id " + id + " nao encontrado em " + caminhoArquivo + ".");
    }

    /** READ (todos): retorna cópia da lista para proteger a coleção interna. */
    public List<T> listarTodos() {
        return new ArrayList<>(registros);
    }

    public boolean existe(int id) {
        for (T registro : registros) {
            if (registro.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private int localizarIndice(int id) {
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).getId() == id) {
                return i;
            }
        }
        throw new EntidadeNaoEncontradaException(
                "Registro com id " + id + " nao encontrado para alteracao.");
    }

    // ---------- Persistência (serialização) ------------------------------

    @SuppressWarnings("unchecked")
    private void carregar() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return; // primeira execução: ainda não há dados
        }
        try (ObjectInputStream entrada =
                     new ObjectInputStream(new FileInputStream(arquivo))) {
            registros = (List<T>) entrada.readObject();
            atualizarProximoId();
        } catch (IOException | ClassNotFoundException e) {
            LogService.erro("Falha ao carregar " + caminhoArquivo, e);
            throw new PersistenciaException("Erro ao ler o arquivo " + caminhoArquivo, e);
        }
    }

    private void persistir() {
        try (ObjectOutputStream saida =
                     new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            saida.writeObject(registros);
        } catch (IOException e) {
            LogService.erro("Falha ao salvar " + caminhoArquivo, e);
            throw new PersistenciaException("Erro ao gravar o arquivo " + caminhoArquivo, e);
        }
    }

    /** Após carregar do disco, garante que novos ids não colidam. */
    private void atualizarProximoId() {
        for (T registro : registros) {
            if (registro.getId() >= proximoId) {
                proximoId = registro.getId() + 1;
            }
        }
    }

    private void garantirPasta() {
        File pasta = new File(PASTA_DADOS);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
    }
}
