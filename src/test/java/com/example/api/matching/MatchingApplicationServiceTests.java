package com.example.api.matching;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.out.persistence.MatchingMapperInterface;
import com.example.api.matching.application.port.out.MatchingApplicationPort;
import com.example.api.matching.dto.MatchingApplicationDto;
import com.example.api.matching.dto.FindMatchingDto;
import com.example.api.matching.service.MatchingApplicationService;
import com.example.api.user.dto.FindUserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchingApplicationServiceTests {
    @InjectMocks
    private MatchingApplicationService matchingApplicationService;
    @InjectMocks
    private MatchingMapperInterface matchingMapper = mock(MatchingMapperInterface.class);
    @Mock
    private MatchingApplicationPort matchingApplicationPort;
    private MatchingApplicationDto matchingApplication;
    
    @BeforeEach
    void beforeEach() {
        matchingApplication = MatchingApplicationDto.builder()
                .userId(2L)
                .matchingId(2L)
                .state(ApplicationStateEnum.Pending)
                .isActive(true)
                .build();
    }
    
    @Test
    void createMatchingApplicationTest() {
        matchingApplicationService.createMatchingApplication(matchingApplication);
        verify(matchingApplicationPort, times(1)).createMatchingApplication(matchingMapper.toDomain(matchingApplication));
    }

    @Test
    void getByUserIdIsAndStateEqualsTest() {
        List<FindMatchingDto> matchingList = matchingApplicationService.getByUserIdIsAndStateEquals(3L, ApplicationStateEnum.Pending);
        verify(matchingApplicationPort, times(1)).getByUserIdIsAndStateEquals(3L, ApplicationStateEnum.Pending);
    }

    @Test
    void getByMatchingIdIsAndStateEqualsTest() {
        List<FindUserDto> userList = matchingApplicationService.getByMatchingIdIsAndStateEquals(1L, ApplicationStateEnum.Approved);
        verify(matchingApplicationPort, times(1)).getByMatchingIdIsAndStateEquals(1L, ApplicationStateEnum.Approved);
    }

    @Test
    void updateMatchingApplicationTest() {
        matchingApplication.setState(ApplicationStateEnum.Approved);
        MatchingApplicationDto matchingApplicationDto = matchingApplicationService.updateMatchingApplication(matchingApplication);
        verify(matchingApplicationPort, times(1)).updateMatchingApplication(matchingMapper.toDomain(matchingApplication));
    }
}