package com.example.todo.service;

import com.example.todo.models.Todo;
import com.example.todo.respository.TodoRespository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService implements ITodoService {

  @Autowired
  private TodoRespository todoRespository;

  @Override
  public List<Todo> getTodoByUser(String user) {
    return todoRespository.findByUsername(user);
  }

  @Override
  public Optional<Todo> getToDoById(long id) {
    return todoRespository.findById(id);
  }

  @Override
  public void updateTodo(Todo todo) {

    todoRespository.save(todo);

  }

  @Override
  public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
    todoRespository.save(new Todo(name,desc,targetDate,isDone));

  }

  @Override
  public void deleteTodo(long id) {
    //check id to do exists
    Optional<Todo> todo= todoRespository.findById(id);
    if (todo.isEmpty()){
      Optional.empty();
    }
    todoRespository.delete(todo.get());

  }

  @Override
  public void saveTodo(Todo todo) {
    todoRespository.save(todo);
  }
}
