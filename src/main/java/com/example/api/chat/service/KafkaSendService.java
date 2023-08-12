package com.example.api.chat.service;

import com.example.api.chat.application.port.in.SendChatUsecase;
import com.example.api.chat.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaSendService implements SendChatUsecase {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Override
    public void send(String topic, Message message) {
        log.info("kafka send");
        kafkaTemplate.send(topic, message);
    }
}
