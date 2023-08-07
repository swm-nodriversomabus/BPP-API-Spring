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
@IdClass(UserToPurposePK.class)
@Table(name="userToPurpose")
public class UserToPurposeEntity {
    @Id
    private Long userId;
    
    @Id
    private Long purposeId;
}