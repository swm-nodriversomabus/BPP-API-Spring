package com.example.api.preference.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovePreferencePK implements Serializable {
    private Long moveId;
    private Long preferenceId;
}