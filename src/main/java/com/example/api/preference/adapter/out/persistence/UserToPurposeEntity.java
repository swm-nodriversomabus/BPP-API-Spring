package com.example.api.preference.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@IdClass(UserToPurposePK.class)
@Table(name="userToPurpose")
public class UserToPurposeEntity {
    @Id
    private Long userId;
    
    @Id
    private Long purposeId;
}