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

   

    
    public T salvar(T entidade) {
        entidade.setId(proximoId++);
        registros.add(entidade);
        persistir();
        LogService.registrar("CADASTRO",
                entidade.getClass().getSimpleName() + " id=" + entidade.getId());
        return entidade;
    }

   
    public void atualizar(T entidade) {
        int indice = localizarIndice(entidade.getId());
        registros.set(indice, entidade);
        persistir();
        LogService.registrar("ALTERACAO",
                entidade.getClass().getSimpleName() + " id=" + entidade.getId());
    }

    
    public void deletar(int id) {
        T removido = buscarPorId(id);
        registros.remove(removido);
        persistir();
        LogService.registrar("DELECAO",
                removido.getClass().getSimpleName() + " id=" + id);
    }

    
    public T buscarPorId(int id) {
        for (T registro : registros) {
            if (registro.getId() == id) {
                return registro;
            }
        }
        throw new EntidadeNaoEncontradaException(
                "Registro com id " + id + " nao encontrado em " + caminhoArquivo + ".");
    }

    
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

    

    @SuppressWarnings("unchecked")
    private void carregar() {
        File arquivo = new File(caminhoArquivo);
        if (!arquivo.exists()) {
            return; 
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
