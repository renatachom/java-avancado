package com.renatachom.gerencimentotarefas.controller;

import com.renatachom.gerencimentotarefas.entity.Funcionario;
import com.renatachom.gerencimentotarefas.service.FuncionarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
@Api(tags = "Funcionários")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    @ApiOperation("Listar todos os funcionários")
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarFuncionarios();
    }

    @GetMapping("/{id}")
    @ApiOperation("Buscar funcionário por ID")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarFuncionarioPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping
    @ApiOperation("Adicionar um novo funcionário")
    public ResponseEntity<Funcionario> adicionarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario novoFuncionario = funcionarioService.adicionarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualizar um funcionário existente")
    public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionarioAtualizado) {
        Funcionario funcionario = funcionarioService.atualizarFuncionario(id, funcionarioAtualizado);
        return ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Excluir um funcionário")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id) {
        funcionarioService.excluirFuncionario(id);
        return ResponseEntity.noContent().build();
    }
}
