package com.example.api.chat.service;


import com.example.api.chat.application.port.in.SubscribeRoomUsecase;
import com.example.api.chat.config.KafkaConsumerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaSubscribeService implements SubscribeRoomUsecase {
    private final KafkaConsumerConfig kafkaConsumerConfig;

    @Override
    public void subscribe(String roomId) {
        kafkaConsumerConfig.createListenerContainerForRoom(roomId); // 구독시 컨슈머 추가
    }
}
