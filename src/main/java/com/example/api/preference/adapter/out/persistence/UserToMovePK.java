package com.example.api.preference.adapter.out.persistence;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserToMovePK implements Serializable {
    private Long userId;
    private Long moveId;
}