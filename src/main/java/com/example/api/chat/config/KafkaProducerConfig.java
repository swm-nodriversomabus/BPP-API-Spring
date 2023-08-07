package com.example.api.chat.config;

import com.example.api.chat.domain.Message;
import com.google.common.collect.ImmutableMap;
import nonapi.io.github.classgraph.json.JSONSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    // Producer 생산을 위한 빈 설정
    // Factory에서 Producer 생산시 필요한 정보를 Map 형태로 전달
    @Bean
    public ProducerFactory<String, Message> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfigurations(), new StringSerializer(), new JsonSerializer<>());
    }

    // Producer 설정값 전달
    @Bean
    public Map<String, Object> producerConfigurations(){
        return ImmutableMap.<String, Object>builder()
                .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
                .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JSONSerializer.class)
                .build();
    }
    // 이벤트 생산을 돕는 Template
    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }
}
