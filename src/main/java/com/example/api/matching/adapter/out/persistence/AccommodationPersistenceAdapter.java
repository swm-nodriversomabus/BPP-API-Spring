package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.application.port.out.AccommodationPort;
import com.example.api.matching.domain.Accommodation;
import com.example.api.matching.repository.AccommodationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
@ComponentScan
public class AccommodationPersistenceAdapter implements AccommodationPort {
    private final MatchingMapperInterface matchingMapper;
    private final AccommodationRepository accommodationRepository;
    
    @Override
    public void createAccommodation(Accommodation accommodation) {
        accommodationRepository.save(matchingMapper.toEntity(accommodation));
    }
}