package com.example.api.matching.application.port.out;

import org.springframework.stereotype.Component;

@Component
public interface DeleteMatchingPort {
    void deleteAllBy();
    void deleteByMatchingId(Long matchingId);
}