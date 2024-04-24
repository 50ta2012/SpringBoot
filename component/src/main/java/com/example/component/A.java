package com.example.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {

  @Autowired
  B b;

  @Autowired
  C c;

  public void methodA() {
    b.methodB();
    c.whoAmI();
  }
}
