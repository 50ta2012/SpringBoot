package com.example.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
  @Autowired
  C c;

  public void printB() {
    System.out.println("B");
  }

  public void methodB() {
    c.whoAmI();
  }
}
