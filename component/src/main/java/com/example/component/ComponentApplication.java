package com.example.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ComponentApplication {

  public static void main(String[] args) {
    SpringApplication.run(ComponentApplication.class, args);

  }

  @Autowired
  A a;

  @Bean
  CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return arg -> {
      a.methodA();
    };
  }
}
