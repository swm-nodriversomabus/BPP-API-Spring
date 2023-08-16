package com.example.api.preference.adapter.out.persistence;

import com.example.api.common.entity.BaseEntity;
import com.example.api.preference.dto.MatchingPreferenceDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(MatchingPreferencePK.class)
@Table(name="matchingPreference")
public class MatchingPreferenceEntity extends BaseEntity {
    @Id
    private Long matchingId;
    
    @Id
    private Long preferenceId;
    
    public MatchingPreferenceDto toDto() {
        return MatchingPreferenceDto.builder()
                .matchingId(matchingId)
                .preferenceId(preferenceId)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}