package com.example.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProcessApplication {

  public static void main(String[] args) {
    SpringApplication.run(ProcessApplication.class, args);
  }

  @Autowired
  MyProcess myProcess;

  @Bean
  CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return arg -> {
      myProcess.doProcess();
    };
  }
}
