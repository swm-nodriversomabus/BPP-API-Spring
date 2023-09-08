package com.example.api.user;

import com.example.api.user.adapter.out.persistence.UserEntity;
import com.example.api.user.adapter.out.persistence.UserMapperInterface;
import com.example.api.user.adapter.out.persistence.UserPersistenceAdapter;
import com.example.api.user.domain.User;
import com.example.api.user.repository.UserRepository;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.mockito.Mockito.*;

@DataJpaTest(properties = "spring.datasource.initialization-mode=never")
@ContextConfiguration(classes = UserPersistenceAdapter.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigurationPackage
public class UserPersistenceAdapterTests {
    @InjectMocks
    private UserPersistenceAdapter userPersistenceAdapter;
    @InjectMocks
    private UserMapperInterface userMapper = mock(UserMapperInterface.class);
    @InjectMocks
    private UserRepository userRepository = mock(UserRepository.class);
    private User user1, user2, user3;
    
    @BeforeEach
    void beforeEach() {
        user1 = User.builder()
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
        user2 = User.builder()
                .userId(2L)
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
        user3 = User.builder()
                .userId(3L)
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
    }
    
    @Test
    void createUserTest() {
        userPersistenceAdapter.createUser(user1);
        userPersistenceAdapter.createUser(user2);
        userPersistenceAdapter.createUser(user3);
        verify(userRepository, times(3)).save(userMapper.toEntity(user1));
    }
    
    @Test
    void getAllByTest() {
        List<UserEntity> userList = userPersistenceAdapter.getAllBy();
        verify(userRepository, times(1)).getAllBy();
    }
    
    @Test
    void getUserByUserIdTest() throws Exception {
        UserEntity userEntity = userPersistenceAdapter.getUserByUserId(2L).orElse(userMapper.toEntity(user2));
        verify(userRepository, times(1)).getUserByUserId(2L);
    }
    
    @Test
    void updateUserTest() {
        user3.setAddress("Gwangju");
        User user = userPersistenceAdapter.updateUser(3L, user3);
        verify(userRepository, times(1)).save(userMapper.toEntity(user3));
    }

    @Test
    void deleteByUserIdTest() {
        userPersistenceAdapter.deleteByUserId(1L);
        List<UserEntity> userList = userPersistenceAdapter.getAllBy();
        verify(userRepository, times(1)).deleteByUserId(1L);
    }
    
    @Test
    void deleteAllByTest() {
        userPersistenceAdapter.deleteAllBy();
        List<UserEntity> userList = userPersistenceAdapter.getAllBy();
        verify(userRepository, times(1)).deleteAllBy();
    }
}