package com.example.api.chat.config;

import com.example.api.chat.domain.Chat;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
@Slf4j
@RefreshScope
public class KafkaConsumerConfig {
    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    private final Map<String, ConcurrentMessageListenerContainer<String, Chat>> listenerContainers = new HashMap<>();
    private final Map<String,Integer> listenerContainerCount = new HashMap<>();
    private final UniqueGroupIdProvider uniqueGroupIdProvider;
    private final KafkaListenerManager kafkaListenerManager;

    // 컨슈머 생산 팩토리
    @Bean
    public ConsumerFactory<String, Chat> consumerFactory() {
        JsonDeserializer<Chat> deserializer = new JsonDeserializer<>();
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
    public ConcurrentKafkaListenerContainerFactory<String, Chat> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Chat> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    /**
     * 컨슈머 등록 -> 이미 map안에 있을시에는 추가하지 않겠다
     * 컨슈머 만든 후 리스너도 같이 추가해서 넣어준다.
     * @param room
     */
    public void createListenerContainerForRoom(String room){
        if(listenerContainers.containsKey(room)){
            listenerContainerCount.put(room,listenerContainerCount.get(room) + 1);
        }else{
            ContainerProperties containerProperties = new ContainerProperties(room);
            containerProperties.setMessageListener((MessageListener<String, Chat>) record -> kafkaListenerManager.handleMessageForRoom(room, record.value()));
            ConcurrentMessageListenerContainer<String, Chat> listenerContainer = new ConcurrentMessageListenerContainer<>(consumerFactory(), containerProperties);
            listenerContainer.start();
            listenerContainers.put(room, listenerContainer);
            listenerContainerCount.put(room, 1);
        }

    }

    public void removeListenerContainerForRoom(String room){
        ConcurrentMessageListenerContainer<String, Chat> listenerContainer = listenerContainers.get(room);
        if(listenerContainer != null){
            listenerContainer.stop();
            listenerContainers.remove(room);
        }
    }


}


