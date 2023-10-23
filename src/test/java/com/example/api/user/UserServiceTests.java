package com.example.api.user;

import com.example.api.sms.application.port.out.CheckVerifiedPhonePort;
import com.example.api.social.application.port.out.FindSocialPort;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.application.port.out.DeleteUserPort;
import com.example.api.user.application.port.out.FindUserPort;
import com.example.api.user.application.port.out.SaveUserPort;
import com.example.api.user.dto.CreateUserDto;
import com.example.api.user.dto.UpdateUserDto;
import com.example.api.user.service.UserService;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

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
    @Mock
    private FindSocialPort findSocialPort;
    @Mock
    private CheckVerifiedPhonePort checkVerifiedPhonePort;
    private CreateUserDto user1, user2, user3;
    private UpdateUserDto newUser;
    private final String userUUID = "09a46fb0-2ae0-4a35-8aad-0a9e4311a1a3";
    
    @BeforeEach
    void beforeEach() {
        userService = new UserService(userMapper, saveUserPort, findUserPort, deleteUserPort, findSocialPort, checkVerifiedPhonePort);
        user1 = CreateUserDto.builder()
                .username("Andrew")
                .gender(UserGenderEnum.Male)
                .age(24)
                .phone("010-9876-5432")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .stateMessage(" ")
                .mannerScore(75)
                .isActive(true)
                .build();
        user2 = CreateUserDto.builder()
                .username("Andrew")
                .gender(UserGenderEnum.Male)
                .age(22)
                .phone("010-8765-4321")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .stateMessage(" ")
                .mannerScore(72)
                .isActive(true)
                .build();
        user3 = CreateUserDto.builder()
                .username("Andrew")
                .gender(UserGenderEnum.Male)
                .age(25)
                .phone("010-7654-3210")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .stateMessage(" ")
                .mannerScore(73)
                .isActive(true)
                .build();
        newUser = UpdateUserDto.builder()
                .username("Andrew")
                .gender(UserGenderEnum.Male)
                .age(24)
                .phone("010-9876-5432")
                .role(UserRoleEnum.User)
                .blacklist(false)
                .stateMessage(" ")
                .mannerScore(77)
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
        userService.getAll();
        verify(findUserPort, times(1)).getAllBy();
    }
    
    @Test
    void getUserByIdTest() {
        userService.getUser();
        verify(findUserPort, times(1)).getByUserId(UUID.fromString(userUUID));
    }
    
    @Test
    void updateUserTest() {
        user1.setMannerScore(77);
        userService.updateUser(newUser);
        verify(saveUserPort, times(1)).updateUser(UUID.fromString(userUUID), userMapper.toDomain(newUser));
    }
    
    @Test
    void deleteUserTest() {
        userService.deleteUser();
        verify(deleteUserPort, times(1)).deleteByUserId(UUID.fromString(userUUID));
    }

    @Test
    void deleteAllTest() {
        userService.deleteAll();
        verify(deleteUserPort, times(1)).deleteAllBy();
    }
}