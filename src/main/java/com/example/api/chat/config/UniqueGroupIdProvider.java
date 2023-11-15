package com.example.api.chat.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

@Component
@Slf4j
public class UniqueGroupIdProvider {
    // 인스턴스마다 유니크한 그룹 아이디를 주기 위하여
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