package org.zensar.kafka.builder;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class KakfaStreamNumberTopologyBuilder {

    private Random random = new Random();

    @Bean
    public NewTopic numberTopic() {
        return TopicBuilder.name("number-topic").build();
    }

    @Bean
    public NewTopic evenTopic() {
        return TopicBuilder.name("even-topic").build();
    }

    @Bean
    public NewTopic oddTopic() {
        return TopicBuilder.name("odd-topic").build();
    }

    @Bean
    public Supplier<Integer> supplyNumbers() {
        Supplier<Integer> supply = () -> {
           return random.nextInt(100);
        };
        return supply;
    }

    @Bean
    public Function<Integer, Integer> processEven() {
        Function<Integer, Integer> proccess = (Integer number) -> {
            return number%2 == 0 ? number : 0;
        };

        return proccess;
    }

    @Bean
    public Function<Integer, Integer> processOdd() {
        Function<Integer, Integer> proccess = (Integer number) -> {
            return number%2 == 1 ? number : 0;
        };

        return proccess;
    }

    @Bean
    public Consumer<Integer> consumeEven() {
        Consumer<Integer> consumer = (Integer number) -> {
            if (number!=0) {
                System.out.println("Even Number " + number);
            }
        };

        return consumer;
    }

    @Bean
    public Consumer<Integer> consumeOdd() {
        Consumer<Integer> consumer = (Integer number) -> {
            if (number!=0) {
                System.out.println("Odd Number " + number);
            }
        };

        return consumer;
    }

}
