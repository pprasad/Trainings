package org.zensar.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.zensar.kafka.dto.AccountDto;

@Component
public class KafkaMessageListener {

    //@KafkaListener(topics = "MessageTopic", groupId = "MsgTopicTest")
    public void fetchKafkaMessages(String message) {
         System.out.println("Received Messages :{}"+ message);
    }

    @KafkaListener(topics = "MessageTopic01", groupId = "MsgTopicTest")
    public void fetchAccountsfromKafka(AccountDto accountDto) {
        System.out.println("Received AccountDetails :{}"+ accountDto);
    }
}
