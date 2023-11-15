package com.example.api.matching.service;

import com.example.api.common.type.Pair;
import com.example.api.matching.adapter.out.persistence.AccommodationEntity;
import com.example.api.matching.adapter.out.persistence.MatchingEntity;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.in.AccommodationUsecase;
import com.example.api.matching.application.port.out.AccommodationPort;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.domain.Accommodation;
import com.example.api.matching.domain.AccommodationMatching;
import com.example.api.matching.dto.AccommodationDto;
import com.example.api.matching.dto.AccommodationMatchingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AccommodationService implements AccommodationUsecase {
    private final FindMatchingPort findMatchingPort;
    private final AccommodationPort accommodationPort;
    private final MatchingMapperInterface matchingMapper;
    
    @Override
    @Transactional
    public void createAccommodation(Long matchingId, AccommodationDto accommodationDto) {
        Accommodation accommodation = matchingMapper.toDomain(accommodationDto);
        accommodation.setMatchingId(matchingId);
        accommodationPort.createAccommodation(accommodation);
    }
    
    @Override
    public List<AccommodationMatchingDto> getAccommodationMatchingList() {
        List<MatchingEntity> matchingList = accommodationPort.getAccommodationMatchingList();
        List<AccommodationMatchingDto> accommodationMatchingList = new ArrayList<>();
        for (MatchingEntity matchingEntity: matchingList) {
            AccommodationMatching accommodationMatching = matchingMapper.toAccommodationDomain(matchingEntity);
            Accommodation accommodation = accommodationPort.getAccommodation(matchingEntity.getMatchingId());
            accommodationMatching.setPrice(accommodation.getPrice());
            accommodationMatching.setPricePerOne(accommodation.getPrice() / accommodationMatching.getCurrentMember());
            accommodationMatching.setRoom(accommodation.getRoom());
            accommodationMatchingList.add(matchingMapper.toDto(accommodationMatching));
        }
        return accommodationMatchingList;
    }
    
    @Override
    public AccommodationDto getAccommodation(Long matchingId) {
        return matchingMapper.toDto(accommodationPort.getAccommodation(matchingId));
    }
    
    @Override
    public List<AccommodationDto> getRecommendedAccommodationList(UUID userId) {
        List<AccommodationEntity> accommodationList = accommodationPort.getAccommodationList();
        List<Pair<Long, Integer>> accommodationPriceList = new ArrayList<>();
        for (AccommodationEntity accommodationData: accommodationList) {
            Optional<MatchingEntity> matchingEntity = findMatchingPort.getByMatchingId(accommodationData.getMatchingId());
            matchingEntity.ifPresent(entity -> accommodationPriceList.add(new Pair<>(accommodationData.getMatchingId(), accommodationData.getPrice() / entity.getMaxMember())));
        }
        accommodationPriceList.sort(Comparator.comparing(Pair::getSecond));
        
        List<AccommodationDto> sortedAccommodationList = new ArrayList<>();
        for (Pair<Long, Integer> accommodationData: accommodationPriceList) {
            sortedAccommodationList.add(this.getAccommodation(accommodationData.getFirst()));
            if (sortedAccommodationList.size() == 10) {
                break;
            }
        }
        return sortedAccommodationList;
    }
    
    @Override
    @Transactional
    public void updateAccommodation(Long matchingId, AccommodationDto accommodationDto) {
        Accommodation accommodation = matchingMapper.toDomain(accommodationDto);
        accommodation.setMatchingId(matchingId);
        accommodationPort.updateAccommodation(accommodation);
    }
    
    @Override
    @Transactional
    public void deleteAccommodation(Long matchingId) {
        Accommodation accommodation = Accommodation.builder()
                .matchingId(matchingId)
                .price(0)
                .pricePerOne(0)
                .room("")
                .build();
        accommodationPort.deleteAccommodation(accommodation);
    }
}