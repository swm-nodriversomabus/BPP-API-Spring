package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.AccommodationDto;

public interface AccommodationUsecase {
    void createAccommodation(Long matchingId, AccommodationDto accommodationDto);
    AccommodationDto getAccommodation(Long matchingId);
    void updateAccommodation(Long matchingId, AccommodationDto accommodationDto);
    void deleteAccommodation(Long matchingId);
}