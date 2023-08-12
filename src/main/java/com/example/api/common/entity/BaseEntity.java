package com.example.api.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 공통 엔티티 정보를 추가하는 공간
 */
@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass // 공통 매핑 정보 필요시 부모 클래스에서 선언된 필드를 상속 받는 클래스에서 그대로 사용
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;
}