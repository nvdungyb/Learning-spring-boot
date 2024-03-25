package com.dzungyb.todo_app.service;

import com.dzungyb.todo_app.model.Todo;
import com.dzungyb.todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepo;

    public List<Todo> listAll() {
        return (List<Todo>) todoRepo.findAll();
    }

    public Todo getTodoById(long id) {
        return todoRepo.findById(id).get();
    }

    public void save(Todo todo) {
        todoRepo.save(todo);
    }

    public void delete(long id) {
        todoRepo.deleteById(id);
    }
}
