package com.example.api.user.adapter.in.rest;

import com.example.api.user.application.port.in.UserUsecase;
import com.example.api.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserUsecase userUsecase;
    
    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userUsecase.createUser(userDto);
    }
    
    @GetMapping("/user")
    public List<UserDto> getAll() {
        return userUsecase.getAll();
    }
    
    @GetMapping("/user/{userId}")
    public Optional<UserDto> getUserById(@PathVariable Long userId) {
        return userUsecase.getUserById(userId);
    }
    
    @PatchMapping("/user")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userUsecase.updateUser(userDto);
    }
    
    @DeleteMapping("/user")
    public void deleteAll() {
        userUsecase.deleteAll();
    }
    
    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userUsecase.deleteUser(userId);
    }
}