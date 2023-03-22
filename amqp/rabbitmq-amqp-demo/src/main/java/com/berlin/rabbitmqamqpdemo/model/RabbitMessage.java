package com.berlin.rabbitmqamqpdemo.model;

public class RabbitMessage {
  private String queueName;
  private String message;

  public String getQueueName() {
    return this.queueName;
  }

  public void setQueueName(String queueName) {
    this.queueName = queueName;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "{" +
        " queueName='" + getQueueName() + "'" +
        ", message='" + getMessage() + "'" +
        "}";
  }
}
