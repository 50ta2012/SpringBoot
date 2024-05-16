package com.example.dynamicschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DynamicScheduleApplication {

  public static void main(String[] args) {
    SpringApplication.run(DynamicScheduleApplication.class, args);
    new MyScheduler().startSchedule("0/1 * * * * ?");
  }

}
