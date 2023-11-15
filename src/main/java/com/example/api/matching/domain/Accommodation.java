package com.example.api.matching.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {
    private Long matchingId;
    private Integer price;
    private Integer pricePerOne;
    private String room;
}