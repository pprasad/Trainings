package org.filkart.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.filkart.dto.BookDto;
import org.filkart.dto.CarDto;
import org.filkart.dto.MobileDto;
import org.filkart.serializers.BookDeserializer;
import org.filkart.serializers.CarDeserializer;
import org.filkart.serializers.MobileDeserializer;
import org.filkart.utils.Constants;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class OrderStream {

    public static Properties getProp() throws  Exception{
        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"Orders-apps");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.ByteArray().getClass());
        return  properties;
    }

    public void fetchOrderDetails() {
        try {
            System.out.println("**** Kafka Streams ******");
            AtomicInteger bOrderCount = new AtomicInteger();
            AtomicInteger mOrderCount = new AtomicInteger();
            AtomicInteger cOrderCount = new AtomicInteger();
            AtomicReference<Double> bTransAmt = new AtomicReference<>(0d);
            AtomicReference<Double> mTransAmt = new AtomicReference<>(0d);
            AtomicReference<Double> cTransAmt = new AtomicReference<>(0d);

            StreamsBuilder builder = new StreamsBuilder();
            KStream<String, byte[]> kStream = builder.stream(Constants.TOPIC_NAME);
            kStream.foreach((key, value) -> {
                if (Constants.ORDER_TYPE.BOOK.getValue().equals(key)) {
                    BookDto bookDto = new BookDeserializer().deserialize(null, value);
                    bOrderCount.getAndIncrement();
                    bTransAmt.set(bookDto.getPrice()+bTransAmt.get());
                    System.out.print("Books_order_count:"+ bOrderCount.get()+",Books_total_transaction_amount:"+bTransAmt.get());
                } else if(Constants.ORDER_TYPE.MOBILE.getValue().equals(key)) {
                    MobileDto mobileDto = new MobileDeserializer().deserialize(null, value);
                    mOrderCount.getAndIncrement();
                    mTransAmt.set(mobileDto.getPrice()+mTransAmt.get());
                    System.out.print(",Mobiles_order_count:"+ mOrderCount.get()+",Mobiles_total_transaction_amount:"+mTransAmt.get());
                } else {
                    CarDto carDto = new CarDeserializer().deserialize(null, value);
                    cOrderCount.getAndIncrement();
                    cOrderCount.getAndIncrement();
                    cTransAmt.set(carDto.getPrice()+cTransAmt.get());
                    System.out.print(",Cars_order_count:"+ cOrderCount.get()+",Cars_total_transaction_amount:"+cTransAmt.get());
                    System.out.println("\n");
                }

            });
            Topology topology = builder.build();
            KafkaStreams streams = new KafkaStreams(topology, getProp());
            streams.start();
        } catch(Exception ex) {
            System.err.println(String.format("Exception : {}", ex.getMessage()));
        }
    }

    public static void main(String args[]) {
        new OrderStream().fetchOrderDetails();
    }
}
