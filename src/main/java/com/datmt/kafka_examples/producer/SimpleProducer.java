package producer;

import org.apache.avro.Protocol;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {

    }

    private static void sendMessage() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
        properties.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        properties.put("schema.registry.url", "http://localhost:8081");

    }
}
