package com.example.api.matching.dto;

import com.example.api.matching.type.MatchingType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

@Data
public class MatchingDto {
    @NotEmpty
    private Long writerId;
    
    @NotEmpty
    private MatchingType type;
    
    @NotEmpty
    private String title;
    
    @NotEmpty
    private String place;
    
    @NotEmpty
    private String content;
    
    @NotEmpty
    private Date startDate;
    
    @NotEmpty
    private Date endDate;
    
    @NotEmpty
    private Integer maxMember;
    
    @NotEmpty
    private Integer minusAge;
    
    @NotEmpty
    private Integer plusAge;
    
    @NotEmpty
    private Integer readCount;
    
    @NotEmpty
    private Integer likeCount;
    
    @NotEmpty
    private Boolean isActive;
}