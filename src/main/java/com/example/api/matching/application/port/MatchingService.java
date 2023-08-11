package com.example.api.matching.application.port;

import com.example.api.matching.adapter.out.persistence.MatchingMapper;
import com.example.api.matching.domain.Matching;
import com.example.api.matching.domain.MatchingEntity;
import com.example.api.matching.application.port.in.MatchingUsecase;
import com.example.api.matching.application.port.out.DeleteMatchingPort;
import com.example.api.matching.application.port.out.FindMatchingPort;
import com.example.api.matching.application.port.out.LikePort;
import com.example.api.matching.application.port.out.SaveMatchingPort;
import com.example.api.matching.dto.MatchingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MatchingService implements MatchingUsecase {
    private final MatchingMapper matchingMapper;
    private final SaveMatchingPort saveMatchingPort;
    private final FindMatchingPort findMatchingPort;
    private final DeleteMatchingPort deleteMatchingPort;
    private final LikePort likePort;

    @Override
    @Transactional
    public MatchingDto createMatching(MatchingDto matchingDto) {
        Matching matching = saveMatchingPort.createMatching(matchingMapper.fromDtoToCreateDomain(matchingDto));
        
    }

    @Override
    public List<MatchingDto> getAll() {
        return findMatchingPort.getAllBy().stream()
                .map(MatchingEntity::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MatchingDto> getMatchingById(Long matchingId) {
        return findMatchingPort.getMatchingByMatchingId(matchingId)
                .map(MatchingEntity::toDto);
    }
    
    @Override
    public int getLikeCount(Long matchingId) {
        return likePort.getLikeCount(matchingId);
    }

    @Override
    @Transactional
    public MatchingDto updateMatching(MatchingDto matchingDto) {
        Matching matching = saveMatchingPort.updateMatching(matchingMapper.fromDtoToUpdateDomain(matchingDto));
        
    }
    
    @Override
    @Transactional
    public void toggleLike(Long userId, Long matchingId) {
        likePort.toggleLike(userId, matchingId);
    }

    @Override
    @Transactional
    public void deleteAll() {
        deleteMatchingPort.deleteAllBy();
    }

    @Override
    @Transactional
    public void deleteMatching(Long matchingId) {
        deleteMatchingPort.deleteByMatchingId(matchingId);
    }
}