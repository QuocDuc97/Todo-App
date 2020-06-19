package com.example.todo.service;

import com.example.todo.models.Todo;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ITodoService {

  //get to do by user
  List<Todo> getTodoByUser(String user);

  //get to do by id
  Optional<Todo> getToDoById(long id);

  //update to do
  void updateTodo(Todo todo);

  //add to do
  void addTodo(String name, String desc, Date targetDate, boolean isDone);

  //delete to do
  void deleteTodo(long id);

  //save to do
  void saveTodo(Todo todo);
}
