package com.example.api.matching.application.port.in;

public interface DeleteMatchingUsecase {
    void deleteAll();
    void deleteMatching(Long matchingId);
}