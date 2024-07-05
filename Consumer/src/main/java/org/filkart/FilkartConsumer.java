package org.filkart;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.filkart.dto.BookDto;
import org.filkart.dto.CarDto;
import org.filkart.dto.MobileDto;
import org.filkart.utils.Constants;
import static org.filkart.utils.Constants.*;

import static org.filkart.utils.Constants.ORDER_TYPE;
import static org.filkart.utils.Constants.CONFIG_PATH;
import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Properties;

public class FilkartConsumer {
    public static Properties getProps(ORDER_TYPE orderType) throws Exception{
        FileInputStream inputStream = new FileInputStream(new File(CONFIG_PATH));
        Properties props = new Properties();
        props.load(inputStream);
        if (ORDER_TYPE.BOOK.getValue().equals(orderType.getValue())) {
            props.put("value.deserializer", BOOK_DESERIALIZER);
        } else if (ORDER_TYPE.MOBILE.getValue().equals(orderType.getValue())) {
            props.put("value.deserializer", MOBILE_DESERIALIZER);
        } else if (ORDER_TYPE.CAR.getValue().equals(orderType.getValue())) {
            props.put("value.deserializer", CAR_DESERIALIZER);
        }
        return props;
    }

    private class BookConsumer implements Runnable {
        @Override
        public void run() {
            try {
               KafkaConsumer<String, BookDto> consumer = new KafkaConsumer<>(getProps(ORDER_TYPE.BOOK));
               consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME, 0)));
               while (true) {
                   System.out.println("<<<Pulling Messages from "+ ORDER_TYPE.BOOK.getValue());
                   ConsumerRecords<String, BookDto> records = consumer.poll(Duration.ofSeconds(5));
                   for (ConsumerRecord record: records) {
                       System.out.println("Received Data from Book:{} Partition "+ record.partition()+" &&" +
                               " data:{}"+ (record.value() instanceof BookDto ? record.value().toString(): record.value()));
                   }
                   System.out.println("<<<Completed Messages from "+ ORDER_TYPE.BOOK.getValue());
               }
            }catch (Exception ex) {
                System.err.println("Exception During BookConsumer"+ ex.getMessage());
            }
        }
    }

    private class MobileConsumer implements Runnable {
        @Override
        public void run() {
            try {
                KafkaConsumer<String, MobileDto> consumer = new KafkaConsumer<>(getProps(ORDER_TYPE.MOBILE));
                consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME, 1)));
                while (true) {
                    System.out.println("<<<Pulling Messages from "+ ORDER_TYPE.MOBILE.getValue());
                    ConsumerRecords<String, MobileDto> records = consumer.poll(Duration.ofSeconds(5));
                    for (ConsumerRecord record: records) {
                        System.out.println("Received Data from Mobile:{} Partition "+ record.partition()+" &&" +
                                " data:{}"+ (record.value() instanceof MobileDto ? record.value().toString(): record.value()) );
                    }
                    System.out.println("<<<Completed Messages from "+ ORDER_TYPE.BOOK.getValue());
                }
            }catch (Exception ex) {
                System.err.println("Exception During MobileConsumer"+ ex.getMessage());
            }
        }
    }

    private class CarConsumer implements Runnable {
        @Override
        public void run() {
            try {
                KafkaConsumer<String, CarDto> consumer = new KafkaConsumer<>(getProps(ORDER_TYPE.CAR));
                consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME, 2)));
                while (true) {
                    System.out.println("<<<Pulling Messages from "+ ORDER_TYPE.CAR.getValue());
                    ConsumerRecords<String, CarDto> records = consumer.poll(Duration.ofSeconds(5));
                    for (ConsumerRecord record: records) {
                        System.out.println("Received Data from Car:{} Partition "+ record.partition()+" &&" +
                                " data:{}"+ (record.value() instanceof CarDto ? record.value().toString(): record.value()));
                    }
                    System.out.println("<<<Completed Messages from "+ ORDER_TYPE.BOOK.getValue());
                }
            }catch (Exception ex) {
                System.err.println("Exception During CarConsumer"+ ex.getMessage());
            }
        }
    }

    public static  void main(String args[]) {
        Thread bookConsumer = new Thread(new FilkartConsumer().new BookConsumer());
        Thread mobileConsumer = new Thread(new FilkartConsumer().new MobileConsumer());
        Thread carConsumer = new Thread(new FilkartConsumer().new CarConsumer());
        bookConsumer.start();
        mobileConsumer.start();
        carConsumer.start();
    }
}
