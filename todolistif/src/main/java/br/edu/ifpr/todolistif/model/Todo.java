package br.edu.ifpr.todolistif.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk do banco

    @Column(nullable = false)
    private String title; //tarefa
    @Column(nullable = false)
    private LocalDateTime createdAt; //data de criacao
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadLine; //prazo para concluir a terefa
    
    private LocalDateTime finishedAt; //data de conclusao da tarefa

    public Todo() {
        this.createdAt = LocalDateTime.now();
    }

    public void markAsFinished() {
        this.finishedAt = LocalDateTime.now();
    }
}