package com.example.api.matching.application.port.out;

public interface DeleteMatchingPort {
    void deleteAllBy();
    void deleteByMatchingId(Long matchingId);
}