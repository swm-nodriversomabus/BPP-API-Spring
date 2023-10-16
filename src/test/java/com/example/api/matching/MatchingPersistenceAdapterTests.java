package com.example.api.matching;

import com.example.api.matching.adapter.out.persistence.LikeEntity;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.adapter.out.persistence.MatchingPersistenceAdapter;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.repository.LikeRepository;
import com.example.api.matching.repository.MatchingRepository;
import com.example.api.matching.type.MatchingTypeEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.Mockito.*;

@DataJpaTest(properties = "spring.datasource.initialization-mode=never")
@ContextConfiguration(classes = MatchingPersistenceAdapter.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigurationPackage
public class MatchingPersistenceAdapterTests {
    @InjectMocks
    private MatchingPersistenceAdapter matchingPersistenceAdapter;
    @InjectMocks
    private MatchingMapperInterface matchingMapper = mock(MatchingMapperInterface.class);
    @InjectMocks
    private MatchingRepository matchingRepository = mock(MatchingRepository.class);
    @InjectMocks
    private LikeRepository likeRepository = mock(LikeRepository.class);
    private Matching matching1, matching2, matching3;
    private LikeEntity like;

    @BeforeEach
    void beforeEach() {
        matching1 = Matching.builder()
                .matchingId(1L)
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
        matching2 = Matching.builder()
                .matchingId(2L)
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
        matching3 = Matching.builder()
                .matchingId(3L)
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
        like = LikeEntity.builder()
                .userId(UUID.fromString("09a46fb0-2ae0-4a35-8aad-0a9e4311a1a3"))
                .matchingId(3L)
                .build();
    }

    @Test
    void createMatchingTest() {
        matchingPersistenceAdapter.createMatching(matching1);
        matchingPersistenceAdapter.createMatching(matching2);
        matchingPersistenceAdapter.createMatching(matching3);
        verify(matchingRepository, times(3)).save(matchingMapper.toEntity(matching1));
    }

    @Test
    void getAllTest() {
        matchingPersistenceAdapter.getAllBy();
        verify(matchingRepository, times(1)).getAllBy();
    }

    @Test
    void getMatchingByIdTest() {
        matchingPersistenceAdapter.getByMatchingId(2L).orElse(matchingMapper.toEntity(matching2));
        verify(matchingRepository, times(1)).getByMatchingId(2L);
    }

    @Test
    void getMatchingByIsActiveTest() {
        matchingPersistenceAdapter.getByIsActive(true);
        verify(matchingRepository, times(1)).getByIsActive(true);
    }

    @Test
    void getLikeCountTest() {
        matchingPersistenceAdapter.getLikeCount(3L);
        verify(likeRepository, times(1)).countByMatchingId(3L);
    }

    @Test
    void updateMatchingTest() {
        matching3.setReadCount(11);
        matchingPersistenceAdapter.updateMatching(3L, matching3);
        verify(matchingRepository, times(1)).save(matchingMapper.toEntity(matching3));
    }

    @Test
    void toggleLikeTest() {
        matchingPersistenceAdapter.toggleLike(like);
    }

    @Test
    void deleteMatching() {
        matchingPersistenceAdapter.deleteByMatchingId(3L);
        verify(matchingRepository, times(1)).deleteByMatchingId(3L);
    }

    @Test
    void deleteAllTest() {
        matchingPersistenceAdapter.deleteAllBy();
        verify(matchingRepository, times(1)).deleteAllBy();
    }
}