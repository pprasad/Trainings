package org.filkart.partition;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.filkart.utils.Constants;

import java.util.List;
import java.util.Map;

public class FilkartPartition implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
       System.out.println("<<<<< FilkartPartition >>>>");
       List<PartitionInfo> partitionInfos = cluster.availablePartitionsForTopic(topic);
       int nofPartitions = partitionInfos.size();
       if (Constants.ORDER_TYPE.MOBILE.getValue().equals(key)) {
            return nofPartitions > 1 ? 1 :0;
       } else if (Constants.ORDER_TYPE.CAR.getValue().equals(key)) {
           return nofPartitions > 1 ? 2 : 0;
       } else if (Constants.ORDER_TYPE.MOBILE.getValue().equals(key)) {
           return nofPartitions > 1 ? 3 : 0;
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
