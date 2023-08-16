package com.example.api.chat.config;

import com.example.api.chat.domain.Message;
import com.example.api.chat.domain.MessageToKafka;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerConfig {
    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    private final Map<String, ConcurrentMessageListenerContainer<String, Message>> listenerContainers = new HashMap<>();
    private final Map<String,Integer> listenerContainerCount = new HashMap<>();
    private final UniqueGroupIdProvider uniqueGroupIdProvider;
    private final KafkaListenerManager kafkaListenerManager;

    // 컨슈머 생산 팩토리
    @Bean
    public ConsumerFactory<String, Message> consumerFactory() {
        JsonDeserializer<Message> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("*"); // 패키지 신뢰 오류 해결
        Map<String, Object> config = ImmutableMap.<String, Object>builder()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress)
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer)
                .put(ConsumerConfig.GROUP_ID_CONFIG, uniqueGroupIdProvider.getUniqueId())
                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest") //earliest면 파티션의 데이터를 모두 읽는다는 의미
                .build();
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }
    // 멀티 스레드 대비 동기화를 제공하는 컨슈머 생산
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public void createListenerContainerForRoom(String room){
        if(listenerContainers.containsKey(room)){
            listenerContainerCount.put(room,listenerContainerCount.get(room) + 1);
        }else{
            ContainerProperties containerProperties = new ContainerProperties(room);
            containerProperties.setMessageListener((MessageListener<String, Message>) record -> kafkaListenerManager.handleMessageForRoom(room, record.value()));
            ConcurrentMessageListenerContainer<String, Message> listenerContainer = new ConcurrentMessageListenerContainer<>(consumerFactory(), containerProperties);
            listenerContainer.start();
            listenerContainers.put(room, listenerContainer);
            listenerContainerCount.put(room, 1);
        }

    }

    public void removeListenerContainerForRoom(String room){
        ConcurrentMessageListenerContainer<String, Message> listenerContainer = listenerContainers.get(room);
        if(listenerContainer != null){
            listenerContainer.stop();
            listenerContainers.remove(room);
        }
    }


}


