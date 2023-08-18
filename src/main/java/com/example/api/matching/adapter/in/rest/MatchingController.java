package com.example.api.matching.adapter.in.rest;

import com.example.api.matching.application.port.in.DeleteMatchingUsecase;
import com.example.api.matching.application.port.in.FindMatchingUsecase;
import com.example.api.matching.application.port.in.LikeUsecase;
import com.example.api.matching.application.port.in.SaveMatchingUsecase;
import com.example.api.matching.dto.MatchingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MatchingController {
    private final SaveMatchingUsecase saveMatchingUsecase;
    private final FindMatchingUsecase findMatchingUsecase;
    private final DeleteMatchingUsecase deleteMatchingUsecase;
    private final LikeUsecase likeUsecase;
    
    @PostMapping("/matching")
    public MatchingDto createMatching(@RequestBody MatchingDto matchingDto) {
        return saveMatchingUsecase.createMatching(matchingDto);
    }
    
    @GetMapping ("/matching")
    public List<MatchingDto> getAll() {
        return findMatchingUsecase.getAll();
    }
    
    @GetMapping("/matching/{matchingId}")
    public Optional<MatchingDto> getMatchingById(@PathVariable Long matchingId) {
        return findMatchingUsecase.getMatchingById(matchingId);
    }
    
    @GetMapping("/matching/{matchingId}/like")
    public int getLikeCount(@PathVariable Long matchingId) {
        return likeUsecase.getLikeCount(matchingId);
    }
    
    @PatchMapping("/matching/{matchingId}")
    public MatchingDto updateMatching(@PathVariable Long matchingId,@RequestBody MatchingDto matchingDto) {
        return saveMatchingUsecase.updateMatching(matchingId, matchingDto);
    }
    
    @PatchMapping("/matching/like")
    public void toggleLike() {
        
    }

    @DeleteMapping("/matching")
    public void deleteAll() {
        deleteMatchingUsecase.deleteAll();
    }
    
    @DeleteMapping("/matching/{matchingId}")
    public void deleteMatching(@PathVariable Long matchingId) {
        deleteMatchingUsecase.deleteMatching(matchingId);
    }
}