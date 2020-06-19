package com.example.todo.models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
//import javax.validation.constraints.Size;

@Entity
@Table(name = "tbltodo")
@Data
@AllArgsConstructor
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  @Column(name = "username",nullable = false)
  private String username;

  @Column(name = "description", nullable = true)
  @Size(max = 10, message = "Enter at laster 10 character...")
  private String description;

  private Date targetDate;

  public Todo(){

  }

  public Todo(String username, String description, Date targetDate, boolean isDone) {
    this.username = username;
    this.description = description;
    this.targetDate = targetDate;
  }
}
