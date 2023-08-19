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

    /**
     * 새 매칭 생성
     * @param matchingDto
     * @return MatchingDto
     */
    @PostMapping("/matching")
    public MatchingDto createMatching(@RequestBody MatchingDto matchingDto) {
        return saveMatchingUsecase.createMatching(matchingDto);
    }

    /**
     * 전체 매칭 목록 조회
     * @return List<MatchingDto>
     */
    @GetMapping ("/matching")
    public List<MatchingDto> getAll() {
        return findMatchingUsecase.getAll();
    }

    /**
     * ID가 matchingID인 매칭 조회
     * @param matchingId
     * @return Optional<MatchingDto>
     */
    @GetMapping("/matching/{matchingId}")
    public Optional<MatchingDto> getMatchingById(@PathVariable Long matchingId) {
        return findMatchingUsecase.getMatchingById(matchingId);
    }

    /**
     * ID가 matchingID인 매칭의 좋아요 수 조회 (미구현)
     * @param matchingId
     * @return int
     */
    @GetMapping("/matching/{matchingId}/like")
    public int getLikeCount(@PathVariable Long matchingId) {
        return likeUsecase.getLikeCount(matchingId);
    }

    /**
     * ID가 matchingId인 매칭 정보 수정
     * @param matchingId
     * @param matchingDto
     * @return MatchingDto
     */
    @PatchMapping("/matching/{matchingId}")
    public MatchingDto updateMatching(@PathVariable Long matchingId,@RequestBody MatchingDto matchingDto) {
        return saveMatchingUsecase.updateMatching(matchingId, matchingDto);
    }

    /**
     * 매칭의 좋아요 토글 (미구현)
     */
    @PatchMapping("/matching/like")
    public void toggleLike() {
        
    }

    /**
     * 전체 매칭 삭제 (비상시 외 사용 금지)
     */
    @DeleteMapping("/matching")
    public void deleteAll() {
        deleteMatchingUsecase.deleteAll();
    }

    /**
     * ID가 matchingId인 매칭 삭제
     * @param matchingId
     */
    @DeleteMapping("/matching/{matchingId}")
    public void deleteMatching(@PathVariable Long matchingId) {
        deleteMatchingUsecase.deleteMatching(matchingId);
    }
}