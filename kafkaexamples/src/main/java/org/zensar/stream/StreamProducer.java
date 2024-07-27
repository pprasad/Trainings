package org.zensar.stream;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.zensar.KafkaExamples;
import org.zensar.OrderProducer;
import org.zensar.dto.BookDto;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class StreamProducer {
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

    public void sendMessage(KafkaProducer<String, String> producer) {
        ProducerRecord<String, String> record = new ProducerRecord<>("SentenceTopic", "sentence", "I am kafka");
        ProducerRecord<String, String> record1 = new ProducerRecord<>("SentenceTopic", "sentence", "Hello kafka");
        ProducerRecord<String, String> record2 = new ProducerRecord<>("SentenceTopic", "sentence", "Kafka developer");
        producer.send(record, new StreamProducer.MessageCallBack());
        producer.close();
    }

    private class MessageCallBack implements Callback {

        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            System.out.println("call back "+ recordMetadata.partition()+" "+recordMetadata.topic());
        }
    }

    public static void main(String args[]) {
        try {
            KafkaProducer producer = new KafkaProducer(getProps());
            new StreamProducer().sendMessage(producer);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
