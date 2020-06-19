package com.example.todo.respository;

import com.example.todo.models.Todo;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRespository extends JpaRepository<Todo,Long> {

  List<Todo> findByUsername(String username);
}
