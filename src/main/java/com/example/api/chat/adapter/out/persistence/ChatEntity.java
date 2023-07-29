package com.example.api.chat.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Table(name="chat")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatId;

    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private Long senderId;

    @Column(nullable = false, length = 6000)
    private String content;

    @Column(nullable = false)
    private Boolean image;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;



}
