package org.filkart;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.filkart.dto.BookDto;
import org.filkart.dto.CarDto;
import org.filkart.dto.MobileDto;
import org.filkart.utils.Constants;
import static org.filkart.utils.Constants.*;

import static org.filkart.utils.Constants.ORDER_TYPE;
import static org.filkart.utils.Constants.CONFIG_PATH;
import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Properties;

public class FilkartProducer {
    public static Properties getProps(ORDER_TYPE orderType) throws Exception{
        FileInputStream inputStream = new FileInputStream(new File(CONFIG_PATH));
        Properties props = new Properties();
        props.load(inputStream);
        if (ORDER_TYPE.BOOK.getValue().equals(orderType.getValue())) {
            props.put("value.serializer", Constants.BOOK_SERIALIZER);
        } else if (ORDER_TYPE.MOBILE.getValue().equals(orderType.getValue())) {
            props.put("value.serializer", Constants.MOBILE_SERIALIZER);
        } else if (ORDER_TYPE.CAR.getValue().equals(orderType.getValue())) {
            props.put("value.serializer", Constants.CAR_SERIALIZER);
        }
        return props;
    }

    private class MessageCallBack implements Callback {
        String msg;
        public MessageCallBack(String msg) {
            this.msg = msg;
        }
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            System.out.println("call back from "+ msg + " Partition::"+ recordMetadata.partition()+" & Topic::"+recordMetadata.topic());
        }
    }
    private class BookPurchaseProducer implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("<<<<Started BookPurchaseProducer>>>>");
                KafkaProducer<String, BookDto> producer = new KafkaProducer<>(getProps(ORDER_TYPE.BOOK));
                BookDto dto = new BookDto(001,"Java Core", "Black Book", 356.90d, LocalDate.now(), "Scott", "scott@gmail.com", "+1 99999999");
                ProducerRecord<String, BookDto> record = new ProducerRecord<>(TOPIC_NAME,ORDER_TYPE.BOOK.getValue(), dto);
                producer.send(record, new MessageCallBack(ORDER_TYPE.BOOK.getValue()));
                producer.close();
                System.out.println("<<<<Completed BookPurchaseProducer>>>>");
            }catch (Exception ex) {
                System.err.println("Exception in Book purchase Thread:::"+ex.getMessage());
            }
        }
    }

    private class MobilePurchaseProducer implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("<<<<Started MobilePurchaseProducer>>>>");
                KafkaProducer<String, MobileDto> producer = new KafkaProducer<>(getProps(ORDER_TYPE.MOBILE));
                MobileDto dto = new MobileDto(001,"Smart Phone V2344", "Samsung f236", 200000d, LocalDate.now(), "Scott", "scott@gmail.com", "99999999");
                ProducerRecord<String, MobileDto> record = new ProducerRecord<>(TOPIC_NAME,ORDER_TYPE.MOBILE.getValue(), dto);
                producer.send(record, new MessageCallBack(ORDER_TYPE.MOBILE.getValue()));
                producer.close();
                System.out.println("<<<<Completed MobilePurchaseProducer>>>>");
            }catch (Exception ex) {
                System.err.println("Exception in Mobile purchase Thread:::"+ex.getMessage());
            }
        }
    }

    private class CarPurchaseProducer implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("<<<<Started CarPurchaseProducer>>>>");
                KafkaProducer<String, CarDto> producer = new KafkaProducer<>(getProps(ORDER_TYPE.CAR));
                CarDto dto = new CarDto(001,"Suzike", "Suzike 4567", "Red", 29900000d, LocalDate.now(), "Scott", "scott@gmail.com", "+1 99999999");
                ProducerRecord<String, CarDto> record = new ProducerRecord<>(TOPIC_NAME,ORDER_TYPE.CAR.getValue(), dto);
                producer.send(record, new MessageCallBack(ORDER_TYPE.CAR.getValue()));
                producer.close();
                System.out.println("<<<<Completed CarPurchaseProducer>>>>");
            }catch (Exception ex) {
                System.err.println("Exception in Car purchase Thread:::"+ex.getMessage());
            }
        }
    }

    public static  void main(String args[]) {
        Thread bookProd = new Thread(new FilkartProducer().new BookPurchaseProducer());
        Thread mobileProd = new Thread(new FilkartProducer().new MobilePurchaseProducer());
        Thread carProd = new Thread(new FilkartProducer().new CarPurchaseProducer());
        bookProd.start();
        mobileProd.start();
        carProd.start();
    }
}
