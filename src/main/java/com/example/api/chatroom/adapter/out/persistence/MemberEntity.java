package com.example.api.chatroom.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MemberPK.class)
@Table(name="member")
public class MemberEntity {
    @Id
    private Long chatroomId;
    
    @Id
    private Long userId;
    
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime outAt;
}