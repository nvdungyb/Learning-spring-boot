package com.dzungyb.todo_app.controller;

import com.dzungyb.todo_app.model.Todo;
import com.dzungyb.todo_app.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public List<Todo> viewHomePage(Model model) {
        List<Todo> listTodo = todoService.listAll();
        model.addAttribute("listTodos", listTodo);
        return listTodo;
    }

    @PostMapping("/delete/{id}")
    public boolean deleteTodo(@PathVariable(name = "id") long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            todoService.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/save")
    public boolean saveTodo(@RequestParam(name = "title") String title, @RequestParam(name = "description") String description) {
        if (title != null && description != null) {
            Todo todo = new Todo(title, description);
            todoService.save(todo);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/update/{id}")
    public boolean updateTodo(@PathVariable("id") long id) {
        Todo todo = todoService.getTodoById(id);
        if (todo != null) {
            todo.setDone(true);
            todoService.save(todo);
            return true;
        }
        return false;
    }

}
