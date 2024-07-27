package org.zensar;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.zensar.dto.UserDto;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

public class KafkaCon {

    public static void main(String args[]) {
       try {
           //KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getProps());
           KafkaConsumer<String, UserDto> consumer = new KafkaConsumer<>(getProps());
           consumer.subscribe(Arrays.asList("zensar_02"));
           //consumer.seek(new TopicPartition("zensar_02", 1), 0);
           while(true) {
               ConsumerRecords<String, UserDto> records = consumer.poll(Duration.ofSeconds(1));
               for (ConsumerRecord r : records) {
                   Object obj = r.value() instanceof UserDto ? ((UserDto)r.value()) : r.value();
                   System.out.println("Messsage :{}" + obj + "-- partitions:{}" + r.partition() + " topic:{}" + r.topic());
               }
               //consumer.close();
           }
       }catch(Exception ex) {
           System.err.println(ex.getMessage());
       }
   }

   public static Properties getProps() throws Exception{
       Properties props = new Properties();
       FileInputStream stream = new FileInputStream(new File("src/main/resources/consumer.properties"));
       props.load(stream);
       props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        /*props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");*/
       return props;
   }

}
