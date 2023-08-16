package com.example.api.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@Component
@Slf4j
public class UniqueGroupIdProvider {
    public String getUniqueId(){
        try {
            String id = InetAddress.getLocalHost().getHostName() + UUID.randomUUID();
            log.info("UNIQUE ID = {}", id);
            return id;

        }catch (UnknownHostException e){
            e.printStackTrace();
            return UUID.randomUUID().toString();
        }
    }
}
