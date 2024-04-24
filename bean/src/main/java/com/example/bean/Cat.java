package com.example.bean;

import org.springframework.stereotype.Component;

@Component
public class Cat {

  private String name = "A-Lu";

  public String getName() {
    return name;
  }
}
