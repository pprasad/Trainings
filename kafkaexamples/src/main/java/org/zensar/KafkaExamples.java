package org.zensar;
import org.apache.kafka.clients.producer.*;
import org.zensar.dto.UserDto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class KafkaExamples {

    public static Properties getProps() throws Exception {
        Properties props = new Properties();
        System.out.println("Path "+ KafkaExamples.class.getClassLoader().getClass().getResource("src/main/resources/producer.properties"));
        FileInputStream stream = new FileInputStream(new File("src/main/resources/producer.properties"));
        props.load(stream);
        /*props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");*/
        return props;
    }


    public void asyncKafka(Producer<String, String> sendMsg) {
        ProducerRecord<String, String> record = new ProducerRecord<>("zensar_02","Amazon.shoes","Bata Shoes");
        ProducerRecord<String, String> record1 = new ProducerRecord<>("zensar_02","Amazon.cars","By Light");
        sendMsg.send(record, new MessageCallBack());
        sendMsg.send(record1, new MessageCallBack());
        sendMsg.close();
    }

    public void asyncKafkaWithCustom(Producer<String, UserDto> sendMsg) {
        ProducerRecord<String, UserDto> record = new ProducerRecord<>("zensar_02","Amazon.shoes", new UserDto(1, "Filkart", 9999999));
        ProducerRecord<String, UserDto> record1 = new ProducerRecord<>("zensar_02","Amazon.cars", new UserDto(2, "Amazon", 9999999));
        sendMsg.send(record, new MessageCallBack());
        sendMsg.send(record1, new MessageCallBack());
        sendMsg.close();
    }

    private class MessageCallBack implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            System.out.println("call back "+ recordMetadata.partition()+" "+recordMetadata.topic());
        }
    }

    public static void main(String args[]) {
        try {
            //Producer<String, String> sendMsg = new KafkaProducer<>(getProps());
            Producer<String, UserDto> sendMsg = new KafkaProducer<>(getProps());
            //ProducerRecord<String, String> record = new ProducerRecord<>("zensar_01","Amazon.order","Laptop clearner sales available");
            //sendMsg.send(record);
            //sendMsg.close();
            new KafkaExamples().asyncKafkaWithCustom(sendMsg);
        }catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
