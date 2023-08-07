package com.example.api.matching.adapter.in.rest;

import com.example.api.matching.application.port.in.MatchingUsecase;
import com.example.api.matching.dto.MatchingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingUsecase matchingUsecase;
    
    @PostMapping("/matching")
    public void createMatching(MatchingDto matchingDto) {
        matchingUsecase.createMatching(matchingDto);
    }
    
    @GetMapping ("/matching")
    public List<MatchingDto> getAll() {
        return matchingUsecase.getAll();
    }
    
    @GetMapping("/matching/{matchingId}")
    public MatchingDto getMatchingById(@PathVariable Long matchingId) {
        return matchingUsecase.getMatchingById(matchingId);
    }
    
    @PatchMapping("/matching/{matchingId}")
    public void updateMatching(@PathVariable Long matchingId, MatchingDto matchingDto) {
        matchingUsecase.updateMatching(matchingId, matchingDto);
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