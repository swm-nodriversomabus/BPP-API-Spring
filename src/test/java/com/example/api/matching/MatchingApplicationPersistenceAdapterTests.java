package com.example.api.matching;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingApplicationPersistenceAdapter;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.domain.MatchingApplication;
import com.example.api.matching.repository.MatchingApplicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.mockito.Mockito.*;

@DataJpaTest(properties = "spring.datasource.initialization-mode=never")
@ContextConfiguration(classes = MatchingApplicationPersistenceAdapter.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigurationPackage
public class MatchingApplicationPersistenceAdapterTests {
    @InjectMocks
    private MatchingApplicationPersistenceAdapter matchingApplicationPersistenceAdapter;
    @InjectMocks
    private MatchingMapperInterface matchingMapper = mock(MatchingMapperInterface.class);
    @InjectMocks
    private MatchingApplicationRepository matchingApplicationRepository = mock(MatchingApplicationRepository.class);
    private MatchingApplication matchingApplication;
    private final UUID userUUID = UUID.fromString("09a46fb0-2ae0-4a35-8aad-0a9e4311a1a3");
    
    @BeforeEach
    void beforeEach() {
        matchingApplication = MatchingApplication.builder()
                .userId(userUUID)
                .matchingId(2L)
                .state(ApplicationStateEnum.Pending)
                .isActive(true)
                .build();
    }

    @Test
    void createMatchingApplicationTest() {
        matchingApplicationPersistenceAdapter.saveMatchingApplication(matchingApplication);
        verify(matchingApplicationRepository, times(1)).save(matchingMapper.toEntity(matchingApplication));
    }

    @Test
    void getByUserIdIsAndStateEqualsTest() {
        matchingApplicationPersistenceAdapter.getByUserIdAndStateEquals(userUUID, ApplicationStateEnum.Pending);
        verify(matchingApplicationRepository, times(1)).getByUserIdAndStateEquals(userUUID, ApplicationStateEnum.Pending);
    }

    @Test
    void getByMatchingIdIsAndStateEqualsTest() {
        matchingApplicationPersistenceAdapter.getByMatchingIdAndStateEquals(1L, ApplicationStateEnum.Approved);
        verify(matchingApplicationRepository, times(1)).getByMatchingIdAndStateEquals(1L, ApplicationStateEnum.Approved);
    }
}