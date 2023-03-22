package com.berlin.rabbitmqamqpdemo;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.berlin.rabbitmqamqpdemo.tool.RabbitReceiver;

@SpringBootApplication
public class RabbitmqAmqpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqAmqpDemoApplication.class, args);
	}

	@Bean
	public Queue hello() {
		return new Queue("queue1");
	}

	@Bean
	public RabbitReceiver receiver() {
		return new RabbitReceiver();
	}

}
