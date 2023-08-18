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
@IdClass(MovePreferencePK.class)
@Table(name="userToMove")
public class MovePreferenceEntity {
    @Id
    private Long moveId;
    
    @Id
    private Long preferenceId;
}