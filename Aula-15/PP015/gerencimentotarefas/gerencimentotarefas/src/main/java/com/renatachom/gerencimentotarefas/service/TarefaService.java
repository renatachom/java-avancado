package com.renatachom.gerencimentotarefas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renatachom.gerencimentotarefas.entity.Tarefa;
import com.renatachom.gerencimentotarefas.repository.TarefaRepository;

@Service
public class TarefaService {

    private static final Logger logger = LoggerFactory.getLogger(TarefaService.class);

    @Autowired
    private TarefaRepository tarefaRepository;

    public List<Tarefa> listarTarefas() {
        logger.info("Listando todas as tarefas");
        return tarefaRepository.findAll();
    }

    public Tarefa buscarTarefaPorId(Long id) {
        logger.info("Buscando tarefa por ID: {}", id);
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com o ID: " + id));
    }

    public Tarefa adicionarTarefa(Tarefa tarefa) {
        logger.info("Adicionando uma nova tarefa: {}", tarefa);
        return tarefaRepository.save(tarefa);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) {
        logger.info("Atualizando tarefa com ID: {}", id);
        Tarefa tarefa = buscarTarefaPorId(id);
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setConcluida(tarefaAtualizada.isConcluida());
        return tarefaRepository.save(tarefa);
    }

    public void excluirTarefa(Long id) {
        logger.info("Excluindo tarefa com ID: {}", id);
        Tarefa tarefa = buscarTarefaPorId(id);
        tarefaRepository.delete(tarefa);
    }
}
