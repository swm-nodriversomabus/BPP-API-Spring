package com.example.api.user.adapter.in.rest;

import com.example.api.matching.dto.MatchingDto;
import com.example.api.user.application.port.in.DeleteUserUsecase;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.application.port.in.RecommendedMatchingUsecase;
import com.example.api.user.application.port.in.SaveUserUsecase;
import com.example.api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final SaveUserUsecase saveUserUsecase;
    private final FindUserUsecase findUserUsecase;
    private final DeleteUserUsecase deleteUserUsecase;
    private final RecommendedMatchingUsecase recommendedMatchingUsecase;
    
    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return saveUserUsecase.createUser(userDto);
    }
    
    @GetMapping("/user")
    public List<UserDto> getAll() {
        return findUserUsecase.getAll();
    }
    
    @GetMapping("/user/{userId}")
    public Optional<UserDto> getUserById(@PathVariable Long userId) {
        return findUserUsecase.getUserById(userId);
    }
    
    @GetMapping("/user/{userId}/recommendedmatching")
    public List<MatchingDto> getRecommendedMatchingList(@PathVariable Long userId) {
        return recommendedMatchingUsecase.getRecommendedMatchingList(userId);
    }
    
    @PatchMapping("/user/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return saveUserUsecase.updateUser(userId, userDto);
    }
    
    @DeleteMapping("/user")
    public void deleteAll() {
        deleteUserUsecase.deleteAll();
    }
    
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        deleteUserUsecase.deleteUser(userId);
    }
}