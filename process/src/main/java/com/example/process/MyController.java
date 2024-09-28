package com.example.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  @Autowired
  MyProcess myProcess;

  @GetMapping("/stop")
  public void stopProcess() {
    myProcess.getProcessList().get(0).destroy();
  }
}
