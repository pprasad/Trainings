package org.zensar.kafka.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zensar.kafka.dto.MessageDto;
import org.zensar.kafka.dto.AccountDto;
import org.zensar.kafka.service.KafkaMessageService;

@RestController
public class KafkaMessageController {

    @Autowired
    KafkaMessageService kafkaMessageService;

    @PostMapping(value = "/kafka-message", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageDto> postNewMessage(@RequestBody MessageDto messageDto) {
        kafkaMessageService.sendMessage(messageDto.getMessage());
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    @PostMapping(value = "/account", consumes= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> postNewMessage(@RequestBody AccountDto accountDto) {
        kafkaMessageService.sendMessage(accountDto);
        return new ResponseEntity<>(accountDto, HttpStatus.OK);
    }
}
