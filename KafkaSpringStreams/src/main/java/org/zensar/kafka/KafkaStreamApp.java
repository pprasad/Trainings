package org.zensar.kafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaStreamApp {
    public static void main(String[] args) {
        SpringApplication.run(KafkaStreamApp.class, args);
    }
}