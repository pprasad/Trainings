package org.zensar.stream;

import ch.qos.logback.classic.net.SyslogAppender;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Arrays;
import java.util.Properties;

public class PriceStreamProcessor {

    public static Properties getProps() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"PriceTopic-applications");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        return  properties;
    }

    public static void main(String []args) {
        StreamsBuilder builder = new StreamsBuilder();

            KStream<String, Integer> kstream = builder.stream("PriceTopic", Consumed.with(Serdes.String(), Serdes.Integer()));
            //kstream.foreach((s, value) -> System.out.println("Skey" + s + " Svalue"+ value));
         KStream<String, Integer> finalValue =  kstream.filter((s, value) -> value.intValue() > 100).
                    map((s, value) -> {
                        return new KeyValue<>(Integer.toString(value.intValue()), value.intValue());
                    }).groupByKey()
                    .count()
                    .mapValues((count) -> count.intValue()).toStream();
            finalValue.to("PriceSinkTopic");


        Topology topology = builder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, getProps());
        kafkaStreams.start();
    }

}
