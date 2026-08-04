package br.edu.ifpr.todolistif.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private LocalDateTime deadLine; //prazo para concluir a terefa
    
    private LocalDateTime finishedAt; //data de conclusao da tarefa

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Todo() {
        this.createdAt = LocalDateTime.now();
    }

    public void markAsFinished() {
        this.finishedAt = LocalDateTime.now();
    }

    //codigo muito legal que funciona
}
