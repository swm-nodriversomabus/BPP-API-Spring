package com.example.api.matching.adapter.in.rest;

import com.example.api.matching.application.port.in.MatchingUsecase;
import com.example.api.matching.dto.MatchingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingUsecase matchingUsecase;
    
    @PostMapping("/matching")
    public MatchingDto createMatching(MatchingDto matchingDto) {
        return matchingUsecase.createMatching(matchingDto);
    }
    
    @GetMapping ("/matching")
    public List<MatchingDto> getAll() {
        return matchingUsecase.getAll();
    }
    
    @GetMapping("/matching/{matchingId}")
    public Optional<MatchingDto> getMatchingById(@PathVariable Long matchingId) {
        return matchingUsecase.getMatchingById(matchingId);
    }
    
    @GetMapping("/matching/{matchingId}/like")
    public int getLikeCount(@PathVariable Long matchingId) {
        return matchingUsecase.getLikeCount(matchingId);
    }
    
    @PatchMapping("/matching")
    public MatchingDto updateMatching(MatchingDto matchingDto) {
        return matchingUsecase.updateMatching(matchingDto);
    }
    
    @PatchMapping("/matching/like")
    public void toggleLike() {
        
    }

    @DeleteMapping("/matching")
    public void deleteAll() {
        matchingUsecase.deleteAll();
    }
    
    @DeleteMapping("/matching/{matchingId}")
    public void deleteMatching(@PathVariable Long matchingId) {
        matchingUsecase.deleteMatching(matchingId);
    }
}