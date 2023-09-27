package com.example.api.social.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Getter
public class AddSocialDto {
    private String id;
    private String provider;
}
