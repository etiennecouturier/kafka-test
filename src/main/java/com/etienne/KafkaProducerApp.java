package com.etienne;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class KafkaProducerApp {

    public static final String TOPIC = "my-numbers";

    public static void main(String[] args) {
        // create instance for properties to access producer configs
        Properties props = new Properties();

        props.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");

        props.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        props.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");

        try(Producer<String, String> producer = new KafkaProducer<>(props)) {
            try {
                for (int i = 0; i < 100; i++)
                    producer.send(new ProducerRecord(TOPIC, Integer.toString(i), "msg: " + i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
