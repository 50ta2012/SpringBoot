package com.example.dynamicschedule;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
  // Seconds Minutes Hours DayOfMonth Month DayOfWeek (Year)
  @GetMapping("/cron/{second}")
  public void resetCron(@PathVariable(value = "second") Integer second) {
    try {
      new MyScheduler().startSchedule("*/%s * * * * ?".formatted(second));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
