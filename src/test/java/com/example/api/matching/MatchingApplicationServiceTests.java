package com.example.api.matching;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.dto.SaveMatchingApplicationDto;
import com.example.api.matching.service.MatchingApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchingApplicationServiceTests {
    @InjectMocks
    private MatchingApplicationService matchingApplicationService;
    @InjectMocks
    private MatchingMapperInterface matchingMapper = mock(MatchingMapperInterface.class);
    @Mock
    private MatchingApplicationPort matchingApplicationPort;
    private SaveMatchingApplicationDto matchingApplication;
    private final String userUUID = "09a46fb0-2ae0-4a35-8aad-0a9e4311a1a3";
    
    @BeforeEach
    void beforeEach() {
        matchingApplication = SaveMatchingApplicationDto.builder()
                .matchingId(2L)
                .state(ApplicationStateEnum.Pending)
                .isActive(true)
                .build();
    }
    
    /*@Test
    void createMatchingApplicationTest() {
        matchingApplicationService.createMatchingApplication(matchingApplication);
        verify(matchingApplicationPort, times(1)).createMatchingApplication(matchingMapper.toDomain(matchingApplication));
    }*/

    @Test
    void getByUserIdIsAndStateEqualsTest() {
        matchingApplicationService.getByUserIdIsAndStateEquals(ApplicationStateEnum.Pending);
        verify(matchingApplicationPort, times(1)).getByUserIdIsAndStateEquals(UUID.fromString(userUUID), ApplicationStateEnum.Pending);
    }

    @Test
    void getByMatchingIdIsAndStateEqualsTest() {
        matchingApplicationService.getByMatchingIdIsAndStateEquals(1L, ApplicationStateEnum.Approved);
        verify(matchingApplicationPort, times(1)).getByMatchingIdIsAndStateEquals(1L, ApplicationStateEnum.Approved);
    }

    @Test
    void updateMatchingApplicationTest() {
        matchingApplication.setState(ApplicationStateEnum.Approved);
        matchingApplicationService.updateMatchingApplication(matchingApplication);
        verify(matchingApplicationPort, times(1)).updateMatchingApplication(matchingMapper.toDomain(matchingApplication));
    }
}