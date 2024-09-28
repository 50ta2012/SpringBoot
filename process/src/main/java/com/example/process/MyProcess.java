package com.example.process;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class MyProcess {
  List<Process> processList = new ArrayList<>();

  public List<Process> getProcessList() {
    return this.processList;
  }

  public void setProcessList(List<Process> processList) {
    this.processList = processList;
  }

  public void doProcess() throws Exception {

    String[] command = {
        "bash",
        "-c",
        "while true; do date; sleep 1; done"
    };

    Process downloadProcess = new ProcessBuilder(command).inheritIO().start();
    processList.add(downloadProcess);

    downloadProcess.waitFor();

  }
}
