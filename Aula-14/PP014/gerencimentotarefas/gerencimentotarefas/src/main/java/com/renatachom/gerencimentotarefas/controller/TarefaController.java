package com.renatachom.gerencimentotarefas.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.renatachom.gerencimentotarefas.entity.Tarefa;
import com.renatachom.gerencimentotarefas.service.TarefaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/tarefas")
@Api(tags = "Tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    @ApiOperation("Listar todas as tarefas")
    public ResponseEntity<List<Tarefa>> listarTarefas() {
        List<Tarefa> tarefas = tarefaService.listarTarefas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar tarefa por ID")
    public ResponseEntity<Tarefa> buscarTarefaPorId(
            @ApiParam(value = "ID da tarefa", required = true) @PathVariable Long id) {
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping
    @ApiOperation("Adicionar uma nova tarefa")
    public ResponseEntity<Tarefa> adicionarTarefa(
            @ApiParam(value = "Dados da nova tarefa", required = true) @RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaService.adicionarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar uma tarefa existente")
    public ResponseEntity<Tarefa> atualizarTarefa(
            @ApiParam(value = "ID da tarefa a ser atualizada", required = true) @PathVariable Long id,
            @ApiParam(value = "Novos dados da tarefa", required = true) @RequestBody Tarefa tarefaAtualizada) {
        Tarefa tarefa = tarefaService.atualizarTarefa(id, tarefaAtualizada);
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Excluir uma tarefa")
    public ResponseEntity<Void> excluirTarefa(
            @ApiParam(value = "ID da tarefa a ser excluída", required = true) @PathVariable Long id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
