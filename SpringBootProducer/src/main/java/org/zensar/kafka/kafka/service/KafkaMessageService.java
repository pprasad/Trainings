package org.zensar.kafka.kafka.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.zensar.kafka.kafka.dto.AccountDto;

@Service
public class KafkaMessageService {

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    @Qualifier("JsonKafkaTemplate")
    KafkaTemplate jsonKafkaTemplate;

    @Autowired
    NewTopic newTopic;

    @Autowired
    @Qualifier("JsonTopic")
    NewTopic jsonTopic;

    /*
     * @param message
     * @return String
     * @Desc sending message to kafka topic
     */
    public String sendMessage(String message) {
        kafkaTemplate.send(newTopic.name(), message);
        return message;
    }

    /*
     * @param AccountDto
     * @return AccountDto
     * @Desc sending message to kafka topic
     */
    public AccountDto sendMessage(AccountDto accountDto) {
        jsonKafkaTemplate.send(jsonTopic.name(), accountDto);
        return accountDto;
    }
}
