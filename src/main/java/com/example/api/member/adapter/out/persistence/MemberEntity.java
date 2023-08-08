package com.example.api.member.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@IdClass(MemberPK.class)
@Table(name="member")
public class MemberEntity extends BaseEntity {
    @Id
    private UUID chatroomId;
    @Id
    private Long userId;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime outAt;
}
