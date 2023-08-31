package com.example.api.preference.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UserPreferencePK implements Serializable {
    private Long userId;
    private Long preferenceId;
}