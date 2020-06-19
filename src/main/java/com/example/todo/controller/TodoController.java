package com.example.todo.controller;

import com.example.todo.models.Todo;
import com.example.todo.service.ITodoService;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TodoController {
  @Autowired
  private ITodoService todoService;

  @InitBinder
  public void initBinder(WebDataBinder binder){
    //date format dd-MM-yyyy
    SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
  }

  @RequestMapping(value = "/list-todos",method = RequestMethod.GET)
  public String showTodos(ModelMap model){
    String name= getLoggedInUserName(model);
    model.put("todos",todoService.getTodoByUser(name));
    return "list-todos";

  }

  public String getLoggedInUserName(ModelMap modelMap){
    Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails){
      System.out.println("userdetali"+principal);
      return ((UserDetails) principal).getUsername();
    }
    return principal.toString();
  }


  @RequestMapping(value = "/add-todo",method = RequestMethod.GET)
  public String showAddTodoPage(ModelMap model){
    model.addAttribute("todo", new Todo());
    return "todo";
  }

  @RequestMapping(value = "/delete-todo",method = RequestMethod.GET)
  public String deleteTodo(@RequestParam long id){
    todoService.deleteTodo(id);
    return "redirect:/list-todos";
  }

  @RequestMapping(value = "/update-todo",method = RequestMethod.GET)
  public String showUpdateTodo(@RequestParam long id, ModelMap modelMap){
    Todo todo= todoService.getToDoById(id).get();
    modelMap.put("todo",todo);
    return "todo";
  }

  @RequestMapping(value = "/update-todo",method = RequestMethod.POST)
  public String updateTodo(ModelMap modelMap, @Valid Todo todo, BindingResult result){

    if (result.hasErrors()){
      return "todo";
    }
    //set userName
    todo.setUsername(getLoggedInUserName(modelMap));
    todoService.updateTodo(todo);
    return "redirect:/list-todo";

  }

  @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
  public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

    if (result.hasErrors()) {
      return "todo";
    }
    todo.setUsername(getLoggedInUserName(model));
    todoService.saveTodo(todo);
    return "redirect:/list-todos";
  }





}
