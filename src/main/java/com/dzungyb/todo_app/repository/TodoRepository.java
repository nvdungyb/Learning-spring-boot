package com.dzungyb.todo_app.repository;

import com.dzungyb.todo_app.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {

}
