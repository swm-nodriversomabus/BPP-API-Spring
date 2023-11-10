package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.AccommodationDto;

public interface AccommodationUsecase {
    void createAccommodation(Long matchingId, AccommodationDto accommodationDto);
}