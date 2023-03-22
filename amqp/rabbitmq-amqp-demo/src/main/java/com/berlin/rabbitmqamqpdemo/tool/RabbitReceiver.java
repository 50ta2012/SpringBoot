package com.berlin.rabbitmqamqpdemo.tool;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "queue1")
public class RabbitReceiver {
  @RabbitHandler
  public void receive(String in) {
    System.out.println(" [x] Received '" + in + "'");
  }
}
