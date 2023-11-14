package com.example.api.matching.application.port.out;

import com.example.api.matching.domain.Accommodation;

public interface AccommodationPort {
    void createAccommodation(Accommodation accommodation);
    Accommodation getAccommodation(Long matchingId);
    void updateAccommodation(Accommodation accommodation);
    void deleteAccommodation(Accommodation accommodation);
}