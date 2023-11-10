package com.example.api.matching.service;

import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.AccommodationUsecase;
import com.example.api.matching.application.port.out.AccommodationPort;
import com.example.api.matching.domain.Accommodation;
import com.example.api.matching.dto.AccommodationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccommodationService implements AccommodationUsecase {
    private final MatchingMapperInterface matchingMapper;
    private final AccommodationPort accommodationPort;
    
    @Override
    @Transactional
    public void createAccommodation(Long matchingId, AccommodationDto accommodationDto) {
        Accommodation accommodation = matchingMapper.toDomain(accommodationDto);
        accommodation.setMatchingId(matchingId);
        accommodationPort.createAccommodation(accommodation);
    }
}