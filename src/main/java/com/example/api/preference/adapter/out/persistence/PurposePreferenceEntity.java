package com.example.api.preference.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PurposePreferencePK.class)
@Table(name="userToPurpose")
public class PurposePreferenceEntity {
    @Id
    private Long purposeId;
    
    @Id
    private Long preferenceId;
}