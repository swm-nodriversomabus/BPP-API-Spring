package com.example.api.preference.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserToMovePK implements Serializable {
    private Long userId;
    private Long moveId;
}