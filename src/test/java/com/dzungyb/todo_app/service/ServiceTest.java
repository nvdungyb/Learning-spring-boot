package com.dzungyb.todo_app.service;

import com.dzungyb.todo_app.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ServiceTest {

    @Autowired(required = true)
    private TodoService todoService;

    @Test
    public void testListAll() {
        List<Todo> ls = todoService.listAll();
        for (Todo todo : ls) {
            System.out.println(todo);
        }
    }

    @Test
    public void saveTodoTest() {
        Todo todo = new Todo();
        todo.setTitle("Math");
        todo.setDescription("Math exam");
        todo.setDone(false);
        todoService.save(todo);
        assertThat(todoService.getTodoById(todo.getId())).isEqualTo(todo);
    }

    @Test
    public void delete() {
        todoService.delete(2);
    }

    @Test
    public void update() {
        Todo todo = todoService.getTodoById(7);
        todo.setDone(true);
        todoService.save(todo);
    }

}
