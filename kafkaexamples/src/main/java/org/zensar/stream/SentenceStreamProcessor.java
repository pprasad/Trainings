package org.zensar.stream;

import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Arrays;
import java.util.Properties;

public class SentenceStreamProcessor {

    public static Properties getProps() {
        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"workcount-application");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return  properties;
    }

    public static void main(String []args) {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> kstream = builder.stream("SentenceTopic");
        KStream<String, String> finalStream = kstream.flatMapValues((sen)-> Arrays.asList(sen.toLowerCase().split(" ")))
                .map((key, value) -> {
                    return new KeyValue<>(value, value);
                }).groupByKey()
                .count()
                .mapValues((count) -> Long.toString(count))
                .toStream();
        finalStream.to("WordCountTopic");
        Topology topology = builder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology, getProps());
        kafkaStreams.start();
    }

}
