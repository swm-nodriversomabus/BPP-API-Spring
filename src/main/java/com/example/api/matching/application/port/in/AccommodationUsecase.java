package com.example.api.matching.application.port.in;

import com.example.api.matching.dto.AccommodationDto;
import com.example.api.matching.dto.AccommodationMatchingDto;

import java.util.List;
import java.util.UUID;

public interface AccommodationUsecase {
    void createAccommodation(Long matchingId, AccommodationDto accommodationDto);
    List<AccommodationMatchingDto> getAccommodationMatchingList();
    AccommodationDto getAccommodation(Long matchingId);
    List<AccommodationDto> getRecommendedAccommodationList(UUID userId);
    void updateAccommodation(Long matchingId, AccommodationDto accommodationDto);
    void deleteAccommodation(Long matchingId);
}