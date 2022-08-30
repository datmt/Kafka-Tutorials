package com.datmt.kafka_examples.avro.producer;

import com.datmt.kafka_examples.TestMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static com.datmt.kafka_examples.helpers.Constants.SIMPLE_AVRO_TOPIC;

public class SimpleAvroProducer {
    public static void main(String[] args) {
        sendMessage();
    }

    private static void sendMessage() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        properties.put("schema.registry.url", "http://localhost:8081");

        try (Producer<String, TestMessage> producer = new KafkaProducer<>(properties)) {
            TestMessage tm = new TestMessage("Hello again", "System");
            ProducerRecord<String, TestMessage> producerRecord = new ProducerRecord<>(SIMPLE_AVRO_TOPIC, tm.getSender().toString(), tm);
            for (int i =0; i < 4; i++)
                producer.send(producerRecord);
        }

    }
}
