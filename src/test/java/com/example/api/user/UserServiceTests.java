package com.example.api.user;

import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.DeleteUserPort;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.application.port.out.SaveUserPort;
import com.example.api.user.dto.FindUserDto;
import com.example.api.user.dto.SaveUserDto;
import com.example.api.user.service.UserService;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {
    @InjectMocks
    private UserService userService;
    @InjectMocks
    private UserMapperInterface userMapper = mock(UserMapperInterface.class);
    @Mock
    private SaveUserPort saveUserPort;
    @Mock
    private FindUserPort findUserPort;
    @Mock
    private DeleteUserPort deleteUserPort;
    private SaveUserDto user1, user2, user3;
    private FindUserDto mockUser1;
    
    @BeforeEach
    void beforeEach() {
        userService = new UserService(userMapper, saveUserPort, findUserPort, deleteUserPort);
        user1 = SaveUserDto.builder()
                .username("Andrew")
                .nickname("TestUser1")
                .gender(UserGenderEnum.Male)
                .age(24)
                .phone("010-9876-5432")
                .email("test1@gmail.com")
                .address("Seoul")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .personality(" ")
                .stateMessage(" ")
                .mannerScore(75)
                .createdUserId(0L)
                .updatedUserId(0L)
                .isActive(true)
                .build();
        user2 = SaveUserDto.builder()
                .username("Andrew")
                .nickname("TestUser1")
                .gender(UserGenderEnum.Male)
                .age(22)
                .phone("010-8765-4321")
                .email("test2@gmail.com")
                .address("Busan")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .personality(" ")
                .stateMessage(" ")
                .mannerScore(72)
                .createdUserId(0L)
                .updatedUserId(0L)
                .isActive(true)
                .build();
        user3 = SaveUserDto.builder()
                .username("Andrew")
                .nickname("TestUser1")
                .gender(UserGenderEnum.Male)
                .age(25)
                .phone("010-7654-3210")
                .email("test3@gmail.com")
                .address("Incheon")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .personality(" ")
                .stateMessage(" ")
                .mannerScore(73)
                .createdUserId(0L)
                .updatedUserId(0L)
                .isActive(true)
                .build();
        mockUser1 = FindUserDto.builder()
                .userId(1L)
                .username("Andrew")
                .nickname("TestUser1")
                .gender(UserGenderEnum.Male)
                .age(24)
                .phone("010-9876-5432")
                .email("test1@gmail.com")
                .address("Seoul")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .personality(" ")
                .stateMessage(" ")
                .mannerScore(75)
                .createdUserId(0L)
                .updatedUserId(0L)
                .isActive(true)
                .build();
    }
    
    @Test
    void createUserTest() {
        userService.createUser(user1);
        userService.createUser(user2);
        userService.createUser(user3);
        verify(saveUserPort, times(3)).createUser(userMapper.toDomain(user1));
    }
    
    @Test
    void getAllTest() {
        List<FindUserDto> userList = userService.getAll();
        verify(findUserPort, times(1)).getAllBy();
    }
    
    @Test
    void getUserByIdTest() throws Exception {
        FindUserDto userDto = userService.getUserById(1L).orElse(mockUser1);
        verify(findUserPort, times(1)).getUserByUserId(1L);
    }
    
    @Test
    void updateUserTest() {
        user1.setAddress("Daegu");
        FindUserDto userDto = userService.updateUser(1L, user1);
        verify(saveUserPort, times(1)).updateUser(1L, userMapper.toDomain(user1));
    }
    
    @Test
    void deleteUserTest() {
        userService.deleteUser(1L);
        verify(deleteUserPort, times(1)).deleteByUserId(1L);
        List<FindUserDto> userList = userService.getAll();
    }

    @Test
    void deleteAllTest() {
        userService.deleteAll();
        verify(deleteUserPort, times(1)).deleteAllBy();
        List<FindUserDto> userList = userService.getAll();
    }
}