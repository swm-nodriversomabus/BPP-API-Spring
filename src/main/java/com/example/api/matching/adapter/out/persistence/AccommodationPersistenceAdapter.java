package com.example.api.matching.adapter.out.persistence;

import com.example.api.matching.application.port.out.AccommodationPort;
import com.example.api.matching.domain.Accommodation;
import com.example.api.matching.repository.AccommodationRepository;
import com.example.api.matching.repository.MatchingRepository;
import com.example.api.matching.type.MatchingTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
@ComponentScan
public class AccommodationPersistenceAdapter implements AccommodationPort {
    private final MatchingRepository matchingRepository;
    private final AccommodationRepository accommodationRepository;
    private final MatchingMapperInterface matchingMapper;
    
    @Override
    public void createAccommodation(Accommodation accommodation) {
        accommodationRepository.save(matchingMapper.toEntity(accommodation));
    }
    
    @Override
    public List<MatchingEntity> getAccommodationMatchingList() {
        return matchingRepository.getByType(MatchingTypeEnum.Accommodation);
    }
    
    @Override
    public List<AccommodationEntity> getAccommodationList() {
        return accommodationRepository.getAllBy();
    }
    
    @Override
    public Accommodation getAccommodation(Long matchingId) {
        return matchingMapper.toDomain(accommodationRepository.getByMatchingId(matchingId).
                orElse(AccommodationEntity.builder()
                        .matchingId(matchingId)
                        .price(0)
                        .pricePerOne(0)
                        .room("")
                        .build()
        ));
    }
    
    @Override
    public void updateAccommodation(Accommodation accommodation) {
        accommodationRepository.save(matchingMapper.toEntity(accommodation));
    }
    
    @Override
    public void deleteAccommodation(Accommodation accommodation) {
        accommodationRepository.save(matchingMapper.toEntity(accommodation));
    }
}