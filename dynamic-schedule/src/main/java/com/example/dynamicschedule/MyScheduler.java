package com.example.dynamicschedule;

import java.time.LocalDateTime;

import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyScheduler {
  static Scheduler scheduler;

  JobDetail job = JobBuilder.newJob(HelloJob.class).build();

  public void startSchedule(String cron) {
    try {
      if (scheduler != null && scheduler.isStarted()) {
        scheduler.shutdown();
      }

      scheduler = new StdSchedulerFactory().getScheduler();

      Trigger trigger = TriggerBuilder.newTrigger()
          .withSchedule(CronScheduleBuilder.cronSchedule(cron))
          .build();

      scheduler.scheduleJob(job, trigger);

      scheduler.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 你要執行的 cron job
  public static class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
      System.out.println(LocalDateTime.now());
    }
  }
}
