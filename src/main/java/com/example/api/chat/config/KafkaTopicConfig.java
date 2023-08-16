package com.example.api.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class KafkaTopicConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapServers;


    // KafkaAdmin 초기화
    @Bean
    public KafkaAdmin kafkaAdmin(){
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    // 애플리케이션 로딩 시점 카프카 토픽 등록
    // 같은 이름의 토픽 등록시 반응 x

    public NewTopic newTopic(String topicName){
        /*
         * --create --replcation-factor = 1 --partitions3 --topioc topic_name --zookeeper이랑 비슷
         */
        return new NewTopic(topicName, 4, (short) 1); // topic이름, numPartitions, replicationFactor
    }

}
