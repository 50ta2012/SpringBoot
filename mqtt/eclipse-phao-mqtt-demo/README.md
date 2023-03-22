# MQTT

## MQTT Server (Mosquitto)

安裝：

```bash
sudo apt update

sudo apt install -y mosquitto

sudo systemctl status mosquitto
```

註冊 MQTT topic

```bash
mosquitto_sub -t $TOPIC_NAME -v
```

> 此處僅留存 Server 端 subscribe 的方法，非必要。

預設 port

```bash
1883
```

## Eclipse Phao Mqtt

EclipsePhaoMqttDemoApplication.java ：

```java
@SpringBootApplication
public class EclipsePhaoMqttDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(EclipsePhaoMqttDemoApplication.class, args);

    final String topic = "mqtt-example";
    final String content = "Example MQTT message sending from Spring Boot ";
    final int qos = 1;
    final String broker = "tcp://localhost:1883";
    final String clientId = "SpringBootSample";
    final MemoryPersistence persistence = new MemoryPersistence();

    try {
      // Connection
      MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);
      System.out.println("Connecting to broker: " + broker);
      sampleClient.connect(connOpts);
      System.out.println("Connected");

      // Subscribe & Receiving
      sampleClient.setCallback(new MqttCallback() {
        public void messageArrived(String topic, MqttMessage message) throws Exception {
          DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
          String now = LocalDateTime.now().format(dateTimeFormatter);

          System.out.println("\nReceived a Message!" +
              "\n\tTime:    " + now +
              "\n\tTopic:   " + topic +
              "\n\tMessage: " + new String(message.getPayload()) +
              "\n\tQoS:     " + message.getQos() + "\n");
        }

        public void connectionLost(Throwable cause) {
          System.out.println("Connection to Solace messaging lost!" + cause.getMessage());
        }

        public void deliveryComplete(IMqttDeliveryToken token) {
        }
      });

      System.out.println("Subscribing client to topic: " + topic);
      sampleClient.subscribe(topic, qos);

      // Sending
      System.out.println("Publishing message: " + content);
      MqttMessage message = new MqttMessage(content.getBytes());
      message.setQos(qos);
      sampleClient.publish(topic, message);

      // Close
      sampleClient.disconnect();
      sampleClient.close();
      System.out.println("Disconnected");
      System.exit(0);
    } catch (MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);
      me.printStackTrace();
    }
  }
}
```

整合 `Eclipse Phao Mqtt` 到 Spring Boot，啟動：

```bash
gradle bootrun
```

Client 輸出：

```bash
Connecting to broker: tcp://localhost:1883
Connected
Subscribing client to topic: mqtt-example
Publishing message: Example MQTT message sending from Spring Boot

Received a Message!
        Time:    2023-03-22 15:08:39.594
        Topic:   mqtt-example
        Message: Example MQTT message sending from Spring Boot
        QoS:     1

Disconnected
```

Server 輸出：

```bash
# 格式：$topic $message
mqtt-example Example MQTT message sending from Spring Boot
```
