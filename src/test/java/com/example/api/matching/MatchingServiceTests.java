package com.example.api.matching;

import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.out.*;
import com.example.api.matching.dto.LikeDto;
import com.example.api.matching.dto.SaveMatchingDto;
import com.example.api.matching.service.MatchingService;
import com.example.api.matching.type.MatchingTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchingServiceTests {
    @InjectMocks
    private MatchingService matchingService;
    @InjectMocks
    private MatchingMapperInterface matchingMapper = mock(MatchingMapperInterface.class);
    @Mock
    private SaveMatchingPort saveMatchingPort;
    @Mock
    private FindMatchingPort findMatchingPort;
    @Mock
    private DeleteMatchingPort deleteMatchingPort;
    @Mock
    private LikePort likePort;
    private SaveMatchingDto matching1, matching2, matching3;
    private LikeDto like;
    private final UUID userId = UUID.fromString("09a46fb0-2ae0-4a35-8aad-0a9e4311a1a3");
    
    @BeforeEach
    void beforeEach() {
        matching1 = SaveMatchingDto.builder()
                .type(MatchingTypeEnum.TravelMate)
                .title("title1")
                .place("Busan")
                .content("content1")
                .startDate(LocalDateTime.of(2023, 9, 4, 15, 0, 0))
                .endDate(LocalDateTime.of(2023, 9, 7, 12, 0, 0))
                .maxMember(6)
                .minusAge(0)
                .plusAge(10)
                .readCount(34)
                .isActive(false)
                .build();
        matching2 = SaveMatchingDto.builder()
                .type(MatchingTypeEnum.TravelMate)
                .title("title2")
                .place("Jeju")
                .content("content2")
                .startDate(LocalDateTime.of(2023, 9, 29, 10, 0, 0))
                .endDate(LocalDateTime.of(2023, 10, 6, 20, 0, 0))
                .maxMember(5)
                .minusAge(0)
                .plusAge(4)
                .readCount(27)
                .isActive(true)
                .build();
        matching3 = SaveMatchingDto.builder()
                .type(MatchingTypeEnum.TravelMate)
                .title("title3")
                .place("Ulsan")
                .content("content3")
                .startDate(LocalDateTime.of(2023, 10, 1, 12, 0, 0))
                .endDate(LocalDateTime.of(2023, 10, 5, 16, 0, 0))
                .maxMember(4)
                .minusAge(5)
                .plusAge(5)
                .readCount(10)
                .isActive(true)
                .build();
        like = LikeDto.builder()
                .userid(userId)
                .matchingId(3L)
                .build();
    }
    
    @Test
    void createMatchingTest() {
        matchingService.createMatching(userId, matching1);
        matchingService.createMatching(userId, matching2);
        matchingService.createMatching(userId, matching3);
        verify(saveMatchingPort, times(3)).createMatching(matchingMapper.toDomain(matching1));
    }
    
    @Test
    void getAllTest() {
        matchingService.getAll();
        verify(findMatchingPort, times(1)).getAllBy();
    }
    
    @Test
    void getMatchingByIdTest() {
        matchingService.getMatchingById(2L);
        verify(findMatchingPort, times(1)).getByMatchingId(2L);
    }
    
    @Test
    void getMatchingByIsActiveTest() {
        matchingService.getMatchingByIsActive(true);
        verify(findMatchingPort, times(1)).getByIsActive(true);
    }
    
    @Test
    void getRecommendedMatchingListTest() {
        matchingService.getRecommendedMatchingList(userId);
    }
    
    @Test
    void getLikeCountTest() {
        matchingService.getLikeCount(3L);
        verify(likePort, times(1)).getLikeCount(3L);
    }
    
    @Test
    void updateMatchingTest() {
        matching3.setReadCount(11);
        matchingService.updateMatching(3L, matching3);
        verify(saveMatchingPort, times(1)).updateMatching(3L, matchingMapper.toDomain(matching3));
    }
    
    @Test
    void toggleLikeTest() {
        matchingService.toggleLike(like);
        verify(likePort, times(1)).toggleLike(refEq(matchingMapper.toEntity(like)));
    }

    @Test
    void deleteMatching() {
        matchingService.deleteMatching(3L);
        verify(deleteMatchingPort, times(1)).deleteByMatchingId(3L);
    }
    
    @Test
    void deleteAllTest() {
        matchingService.deleteAll();
        verify(deleteMatchingPort, times(1)).deleteAllBy();
    }
}