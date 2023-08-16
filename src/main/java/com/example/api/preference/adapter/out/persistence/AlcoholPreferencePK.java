package com.example.api.preference.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlcoholPreferencePK implements Serializable {
    private Long alcoholId;
    private Long preferenceId;
}