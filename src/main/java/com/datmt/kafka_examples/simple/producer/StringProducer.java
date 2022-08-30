package com.datmt.kafka_examples.simple.producer;

import com.datmt.kafka_examples.TestMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static com.datmt.kafka_examples.helpers.Constants.SIMPLE_AVRO_TOPIC;
import static com.datmt.kafka_examples.helpers.Constants.SIMPLE_STRING_TOPIC;

public class StringProducer {
    public static void main(String[] args) {
       sendMessage();
    }


    private static void sendMessage() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("schema.registry.url", "http://localhost:8081");

        try (Producer<String, String> producer = new KafkaProducer<>(properties)) {
            for (int i =0; i < 4; i++) {
                ProducerRecord<String, String> producerRecord = new ProducerRecord<>(SIMPLE_STRING_TOPIC, "Message key: " + i, "Hello simple message" );
                producer.send(producerRecord);
            }
        }

    }
}
