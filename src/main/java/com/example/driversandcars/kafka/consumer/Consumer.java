package com.example.driversandcars.kafka.consumer;

import com.example.driversandcars.dto.KafkaMessageDto;
import com.example.driversandcars.dto.KafkaMessageDtoConsumer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
//@EnableKafka
public class Consumer {

//    @KafkaListener(topics = "TestTopic", containerFactory = "kafkaListenerContainerFactory", groupId = "test-consumer-group")
    public void listenTopic(@Payload ConsumerRecord<String, String> consumerRecord) {
        log.info("Message commited - {}", consumerRecord.value());
        Gson gson = new Gson();
        KafkaMessageDtoConsumer kafkaMessageDto = gson.fromJson(consumerRecord.value(), KafkaMessageDtoConsumer.class);
        log.info("result: {}, {}", kafkaMessageDto, LocalDateTime.parse("2021-10-12T12:13:14"));
    }

}
