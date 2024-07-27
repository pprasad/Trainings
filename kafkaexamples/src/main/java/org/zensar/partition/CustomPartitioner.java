package org.zensar.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

public class CustomPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keybytes, Object value, byte[] valuebytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.availablePartitionsForTopic(topic);
        int noOfpartitions = partitions.size();
        System.out.println("Noof partitions :{}"+ partitions);
        if (key==null || key.equals("")) {
            return 0;
        } else if("Amazon.shoes".equals(key)) {
            return noOfpartitions> 1 ? 1:0;
        } else if ("Amazon.cars".equals(key)) {
            return noOfpartitions> 1 ? 2:0;
        } else if ("book.purchase".equals(key)) {
            return noOfpartitions> 1 ? 2:0;
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
