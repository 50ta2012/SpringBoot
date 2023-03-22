package com.berlin.rabbitmqamqpdemo.api;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.berlin.rabbitmqamqpdemo.model.RabbitMessage;

@RestController
public class RabbitController {

  @Autowired
  private RabbitTemplate template;

  @PostMapping("/send")
  public String sendRabbitMessage(@RequestBody RabbitMessage rabbitMessage) {

    this.template.convertAndSend(rabbitMessage.getQueueName(), rabbitMessage.getMessage());
    System.out.println(" [x] Sent '" + rabbitMessage.getMessage() + "'");

    return "Success";
  }
}
