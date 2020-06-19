package com.example.todo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Controller;

@Configuration
@EnableWebSecurity
public class SercurityConfigurarion extends WebSecurityConfigurerAdapter {

  @Autowired
  public  void configuareGlobalSercurity(AuthenticationManagerBuilder auth) {

    try {
      auth.inMemoryAuthentication()
          .passwordEncoder(NoOpPasswordEncoder.getInstance())
          .withUser("admin").password("admin")
          .roles("USER","ADMIN");
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests().antMatchers("/login","/h2-console/**")
        .permitAll()
        .antMatchers("/","/*todo*/**")
        .access("hasRole('USER')")
        .and().formLogin();

    http.csrf().disable();
    http.headers().frameOptions().disable();
  }
}
