package com.example.api.preference.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserToAlcoholPK implements Serializable {
    private Long userId;
    private Long alcoholId;
}