package com.etienne;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.etienne.KafkaProducerApp.TOPIC;

public class KafkaSubscriberApp4 {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9093");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "test");

        try(KafkaConsumer kafkaConsumer = new KafkaConsumer(props)) {
            List<String> topics = new ArrayList<>();
            topics.add(TOPIC);
            kafkaConsumer.subscribe(topics);

            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(10);
                        records.forEach(record -> System.out.println(
                                String.format("Topic: %s, partition: %d, offset: %d, key: %s, value: %s",
                                        record.topic(), record.partition(), record.offset(), record.key(), record.value().toUpperCase())
                        ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
