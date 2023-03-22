# RabbitMQ

## 啟動 RabbitMQ

```bash
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
```

## Spring Boot

啟動：

```bash
gradle bootrun
```

測試 send 請求：

```bash
http :8080/send queueName=queue1 message="rabbitmq hello world"
```

## 參考

[MQTT vs AMQP](https://stackoverflow.com/a/39616652)
