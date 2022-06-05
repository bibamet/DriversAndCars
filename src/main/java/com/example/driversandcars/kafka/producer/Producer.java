package com.example.driversandcars.kafka.producer;

import com.example.driversandcars.dto.KafkaMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, KafkaMessageDto> kafkaTemplate;

//    @Scheduled(fixedDelay = 5000)
    public void sendMessage(KafkaMessageDto kafkaMessageDto) {
//        KafkaMessageDto message = KafkaMessageDto.builder()
//                .method("test")
//                .clientHost("127.0.0.1")
//                .exception("don't have an exception")
//                .executionTime(12512121L)
//                .result("true")
//                .timeStamp(Timestamp.from(Instant.now()).getTime())
//                .build();
//        kafkaTemplate.send("TestTopic", message);
        kafkaTemplate.send("TestTopic", kafkaMessageDto);
    }

}
