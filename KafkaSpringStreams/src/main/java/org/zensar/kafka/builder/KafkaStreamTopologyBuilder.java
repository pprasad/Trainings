package org.zensar.kafka.builder;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.Function;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaStreamTopologyBuilder {

      private Random random = new Random();

      @Bean
      public NewTopic firstTopic() {
          return TopicBuilder.name("first-topic").build();
      }

      @Bean
      public NewTopic seconTopic() {
          return TopicBuilder.name("second-topic").build();
      }

      //Producer
      @Bean
      public Supplier<String> produceMessages() {
          System.out.print("**produceMessages**");
          Supplier<String> messageSuplier = () -> {
              return "Hello From Kafka Stream " + random.nextInt(100);
          };
          return messageSuplier;
      }

      //processor
      @Bean
      public Function<String, String> processMessages() {
          Function<String, String> func = (String msg) -> {
             return msg.toUpperCase();
          };
          return func;
      }

      //consumer
      @Bean
      public Consumer<String> consumerMessages() {
          Consumer<String> consumer = (String message) -> {
               System.out.println("Consumed Messages:"+ message);
          };
          return consumer;
      }
}
