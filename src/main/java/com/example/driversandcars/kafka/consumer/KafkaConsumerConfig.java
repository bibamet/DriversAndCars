package com.example.driversandcars.kafka.consumer;

import com.example.driversandcars.dto.KafkaMessageDtoConsumer;
import com.example.driversandcars.kafka.DefaultKafkaConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class KafkaConsumerConfig extends DefaultKafkaConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaMessageDtoConsumer> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaMessageDtoConsumer> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setMessageConverter(new StringJsonMessageConverter());
        factory.setConcurrency(1);
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public ConsumerFactory<String, KafkaMessageDtoConsumer> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(getConsumerProps(), Serdes.String().deserializer(), new JsonDeserializer<>(KafkaMessageDtoConsumer.class, false));
    }

}
