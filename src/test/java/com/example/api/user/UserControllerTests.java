package com.example.api.user;

import com.example.api.matching.application.port.in.MatchingApplicationUsecase;
import com.example.api.user.adapter.in.rest.UserController;
import com.example.api.user.application.port.in.DeleteUserUsecase;
import com.example.api.user.application.port.in.FindUserUsecase;
import com.example.api.user.application.port.in.RecommendedMatchingUsecase;
import com.example.api.user.application.port.in.SaveUserUsecase;
import com.example.api.user.type.UserGenderEnum;
import com.example.api.user.type.UserRoleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = UserController.class)
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SaveUserUsecase saveUserUsecase;
    @MockBean
    private FindUserUsecase findUserUsecase;
    @MockBean
    private DeleteUserUsecase deleteUserUsecase;
    @MockBean
    private RecommendedMatchingUsecase recommendedMatchingUsecase;
    @MockBean
    private MatchingApplicationUsecase matchingApplicationUsecase;
    
    static Map<String, Object> user1 = new HashMap<>();
    static Map<String, Object> user2 = new HashMap<>();
    static Map<String, Object> user3 = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    
    @BeforeAll
    static void beforeAll() {
        user1.put("username", "Andrew");
        user1.put("nickname", "TestUser1");
        user1.put("gender", UserGenderEnum.Male);
        user1.put("age", 24);
        user1.put("phone", "010-9876-5432");
        user1.put("email", "test1@gmail.com");
        user1.put("address", "Seoul");
        user1.put("role", UserRoleEnum.User);
        user1.put("blacklist", false);
        user1.put("personality", " ");
        user1.put("stateMessage", " ");
        user1.put("mannerScore", 75);
        user1.put("createdUserId", 0);
        user1.put("updatedUserId", 0);
        user1.put("isActive", true);
        user2.put("username", "Brian");
        user2.put("nickname", "TestUser2");
        user2.put("gender", UserGenderEnum.Male);
        user2.put("age", 22);
        user2.put("phone", "010-8765-4321");
        user2.put("email", "test2@gmail.com");
        user2.put("address", "Busan");
        user2.put("role", UserRoleEnum.User);
        user2.put("blacklist", false);
        user2.put("personality", " ");
        user2.put("stateMessage", " ");
        user2.put("mannerScore", 72);
        user2.put("createdUserId", 0);
        user2.put("updatedUserId", 0);
        user2.put("isActive", true);
        user3.put("username", "Chris");
        user3.put("nickname", "TestUser3");
        user3.put("gender", UserGenderEnum.Female);
        user3.put("age", 25);
        user3.put("phone", "010-7654-3210");
        user3.put("email", "test3@gmail.com");
        user3.put("address", "Incheon");
        user3.put("role", UserRoleEnum.User);
        user3.put("blacklist", false);
        user3.put("personality", " ");
        user3.put("stateMessage", " ");
        user3.put("mannerScore", 73);
        user3.put("createdUserId", 0);
        user3.put("updatedUserId", 0);
        user3.put("isActive", true);
    }
    
    @Test
    void createUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user2)))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user3)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/{userId}",3)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    void updateUserTest() throws Exception {
        user1.replace("address", "Daegu");
        mockMvc.perform(MockMvcRequestBuilders.patch("/user/{userId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user1)))
                .andExpect(status().isOk());
    }
    
    @Test
    void deleteAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/user/{userId}", 1))
                .andExpect(status().isOk());
    }
}