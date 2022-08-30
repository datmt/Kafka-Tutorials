package com.datmt.kafka_examples.simple.consumer;

import com.datmt.kafka_examples.TestMessage;
import com.datmt.kafka_examples.helpers.Constants;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class StringConsumer {
    public static void main(String[] args) {
        consumeMessage();
    }

    private static void consumeMessage() {

        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "kafka.simple_string_consumer");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("schema.registry.url", "http://localhost:8081");


        try (KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(List.of(Constants.SIMPLE_STRING_TOPIC));
            while (true) {
                ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(3));

                for (var message: messages) {
                    System.out.printf("Consuming message with key: %s and value: %s \n",message.key(),  message.value());
                }
            }
        }

    }
}
