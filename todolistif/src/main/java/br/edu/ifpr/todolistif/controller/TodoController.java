package br.edu.ifpr.todolistif.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifpr.todolistif.model.Todo;
import br.edu.ifpr.todolistif.repository.TodoRepository;
import org.springframework.web.servlet.ModelAndView;


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
    
}
