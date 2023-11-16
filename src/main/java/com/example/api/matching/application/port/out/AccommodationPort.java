package com.example.api.matching.application.port.out;

import com.example.api.matching.adapter.out.persistence.AccommodationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.domain.Accommodation;

import java.util.List;

public interface AccommodationPort {
    void createAccommodation(Accommodation accommodation);
    List<MatchingEntity> getAccommodationMatchingList();
    List<AccommodationEntity> getAccommodationList();
    Accommodation getAccommodation(Long matchingId);
    void updateAccommodation(Accommodation accommodation);
    void deleteAccommodation(Accommodation accommodation);
}