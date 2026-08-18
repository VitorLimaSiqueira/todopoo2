package br.edu.ifpr.todolistif.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifpr.todolistif.model.Todo;
import br.edu.ifpr.todolistif.repository.TodoRepository;

@Controller
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @PostMapping("/create")
    public String create(Todo todo) {
        todoRepository.save(todo);
        return "redirect:/";
    }

    @GetMapping("/")
    public ModelAndView list() {
        return new ModelAndView("index", Map.of("todos", todoRepository.findAll()));
    }

    @GetMapping("/teste/todos")
    public List<Todo> listJson() {
        return todoRepository.findAll();
    }

    // Situação 1: edição de uma tarefa inexistente
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("A tarefa que você tentou editar não foi encontrada."));
        return new ModelAndView("edit", Map.of("todo", todo));
    }

    @PostMapping("/update")
    public String update(Todo todo) {
        if (!todoRepository.existsById(todo.getId())) {
            throw new TodoNotFoundException("A tarefa que você tentou atualizar não foi encontrada.");
        }
        todoRepository.save(todo);
        return "redirect:/";
    }

    // Situação 2: exclusão de uma tarefa inexistente
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException("Não foi possível excluir: a tarefa não existe ou já foi removida.");
        }
        todoRepository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/concluir")
    public String concluir(@RequestParam Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException("A tarefa que você tentou concluir não foi encontrada."));
        todo.markAsFinished();
        todoRepository.save(todo);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    static class TodoNotFoundException extends RuntimeException {
        public TodoNotFoundException(String message) {
            super(message);
        }
    }
}
