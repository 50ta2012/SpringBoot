package com.example.futureawaitall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FutureAwaitallApplication {

  public static void main(String[] args) {
    SpringApplication.run(FutureAwaitallApplication.class, args);

    // System.out.println("Without await all process");
    // long start = System.currentTimeMillis();
    // mySleep(1);
    // mySleep(2);
    // mySleep(3);
    // System.out.println("exec time: " + (System.currentTimeMillis() - start) + "
    // ms");

    System.out.println("Await all process");
    long start = System.currentTimeMillis();

    List<CompletableFuture<Void>> taskList = new ArrayList<>();
    taskList.add(completableFutureMySleep(1));
    taskList.add(completableFutureMySleep(2));
    taskList.add(completableFutureMySleep(3));

    CompletableFuture.allOf(taskList.toArray(new CompletableFuture[0])).join();

    System.out.println("exec time: " + (System.currentTimeMillis() - start) + " ms");

  }

  public static void mySleep(long i) {
    try {
      System.out.println("Wait " + i + " secs");
      TimeUnit.SECONDS.sleep(i);
      System.out.println("finished " + i);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static CompletableFuture<Void> completableFutureMySleep(long i) {
    ExecutorService exec = Executors.newSingleThreadExecutor();

    return CompletableFuture.runAsync(() -> {
      mySleep(i);
      exec.shutdown();
    }, exec);
  }
}
