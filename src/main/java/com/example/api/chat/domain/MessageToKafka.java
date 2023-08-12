package com.example.api.chat.domain;

import com.example.api.chat.adapter.out.persistence.ChatEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * kafka에서 메시지 던달을 위해 사용할 도메인 모델
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageToKafka implements Serializable {
    private Long chatId;
    private Long senderId;
    private long createdAt;
    private Long roomId;
    @NotNull
    private String content;
    private Integer readCount;
    private Boolean image;

    public void setSendTimeAndSender(LocalDateTime createdAt, Long senderId, Integer readCount){
        this.senderId = senderId;
        this.createdAt = createdAt.atZone(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
        this.readCount = readCount;
    }
    public ChatEntity toChatEntity(){
        return ChatEntity.builder()
                .senderId(senderId)
                .roomId(roomId)
                .readCount(readCount)
                .image(image)
                .content(content)
                .build();
    }
    public void setId(Long chatId){
        this.chatId = chatId;
    }



}
