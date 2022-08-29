package com.datmt.kafka_examples.consumer;

import com.datmt.kafka_examples.TestMessage;
import com.datmt.kafka_examples.helpers.Constants;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SimpleConsumer {
    public static void main(String[] args) {
       consumeMessage();
    }

    private static void consumeMessage() {

        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "kafka.simple_consumer2");
        properties.put("enable.auto.commit", "true");

        properties.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        properties.put("schema.registry.url", "http://localhost:8081");

        try (KafkaConsumer<String, TestMessage> consumer = new KafkaConsumer<String, TestMessage>(properties)) {
            consumer.subscribe(List.of(Constants.SIMPLE_TOPIC));
            while (true) {
                ConsumerRecords<String, TestMessage> messages = consumer.poll(Duration.ofSeconds(3));

                for (var message: messages) {
                    System.out.printf("messages %s", message.value());
                }
            }
        }

    }
}
