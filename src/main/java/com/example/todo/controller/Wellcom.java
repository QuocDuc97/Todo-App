package com.example.todo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Wellcom {
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String showWelcomPage(ModelMap modelMap){
    modelMap.addAttribute("name",getLoggedinUserName());
    return "welcome";

  }

  private String getLoggedinUserName(){
    Object principal = SecurityContextHolder.getContext()
        .getAuthentication().getPrincipal();

    if (principal instanceof UserDetails){
      System.out.println("hello "+((UserDetails) principal).getUsername());
      return ((UserDetails) principal).getUsername();
    }
    return principal.toString();
  }


}
