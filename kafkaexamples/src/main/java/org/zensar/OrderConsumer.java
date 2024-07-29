package org.zensar;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.zensar.dto.BookDto;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class OrderConsumer {
    private static String TOPIC_NAME = "online_orders";
    private static String KEY ="book.purchase";
    public static Properties getProps() throws Exception {
        Properties props = new Properties();
        FileInputStream stream = new FileInputStream(new File("src/main/resources/consumer.properties"));
        props.load(stream);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    public void receiver(KafkaConsumer<String, BookDto> consumer) {
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
        while(true) {
            ConsumerRecords<String, BookDto> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            for(ConsumerRecord rd: consumerRecords) {
                System.out.println("Recived Data : {}"+ rd.value());
                if ( rd.value() instanceof BookDto) {
                    System.out.println(" Values:{}" + rd.value().toString());
                }
            }
        }
    }

    public void receiverInteger(KafkaConsumer<String, Integer> consumer) {
        consumer.subscribe(Arrays.asList("PriceSinkTopic"));
        while(true) {
            ConsumerRecords<String, Integer> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            for(ConsumerRecord rd: consumerRecords) {
                System.out.println("Recived Data : {}"+ rd.key()+ " value:{}"+rd.value());

            }
        }
    }

    public static void main(String args[]) {
        try {
            KafkaConsumer consumer = new KafkaConsumer(getProps());
            new OrderConsumer().receiverInteger(consumer);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
