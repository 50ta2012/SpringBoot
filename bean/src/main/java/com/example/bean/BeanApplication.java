package com.example.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BeanApplication {
  public static void main(String[] args) {
    SpringApplication.run(BeanApplication.class, args);

  }

  @Autowired
  Cat cat;

  @Bean
  CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return arg -> {
      System.out.println(cat.getName());
    };
  }

}
