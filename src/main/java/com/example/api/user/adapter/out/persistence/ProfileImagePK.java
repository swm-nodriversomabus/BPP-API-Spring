package com.example.api.user.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProfileImagePK implements Serializable {
    private UUID userId;
}