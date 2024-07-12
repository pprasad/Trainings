package org.zensar.kafka.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Primary
    @Bean
    public NewTopic getNewTopic() {
        NewTopic newTopic = new NewTopic("MessageTopic",2, (short)1);
        return newTopic;
    }

    @Bean(name = "JsonTopic")
    public NewTopic getJsonTopic() {
        NewTopic newTopic = new NewTopic("MessageTopic01",2, (short)1);
        return newTopic;
    }

    @Bean
    @Primary
    public KafkaTemplate<String, String> getKafkaTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        return  new KafkaTemplate<>(producerFactory);
    }

    @Bean(name = "JsonKafkaTemplate")
    public KafkaTemplate<String, String> getJsonKafkaTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer");
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(props);
        return  new KafkaTemplate<>(producerFactory);
    }
    // ignore below methods
    /*@Bean(name = "JsonKafkaTemplate")
    public KafkaTemplate<String, AccountDto> getJsonKafkaTemplate() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer");
        ProducerFactory<String, AccountDto> producerFactory = new DefaultKafkaProducerFactory<>(props);
        return  new KafkaTemplate<>(producerFactory);
    }*/

}
