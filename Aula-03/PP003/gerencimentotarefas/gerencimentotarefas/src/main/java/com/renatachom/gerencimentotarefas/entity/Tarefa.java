package com.renatachom.gerencimentotarefas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

import com.renatachom.gerencimentotarefas.entity.enums.PrioridadeTarefa;
import com.renatachom.gerencimentotarefas.entity.enums.StatusTarefa;



@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String titulo;
    private String descricao;
    private PrioridadeTarefa prioridade;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private StatusTarefa status;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    // Getters e Setters

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        if (funcionario != null) {
            funcionario.addTarefa(this);
        }
    }
}
