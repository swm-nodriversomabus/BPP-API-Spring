package com.example.api.matching;

import com.example.api.common.type.ApplicationStateEnum;
import com.example.api.matching.adapter.in.rest.MatchingController;
import com.example.api.matching.application.port.in.*;
import com.example.api.matching.type.MatchingTypeEnum;
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

@SpringBootTest(classes = MatchingController.class)
@AutoConfigureMockMvc
public class MatchingControllerTests {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    SaveMatchingUsecase saveMatchingUsecase;
    @MockBean
    FindMatchingUsecase findMatchingUsecase;
    @MockBean
    DeleteMatchingUsecase deleteMatchingUsecase;
    @MockBean
    MatchingApplicationUsecase matchingApplicationUsecase;
    @MockBean
    LikeUsecase likeUsecase;
    
    static Map<String, Object> matching1 = new HashMap<>();
    static Map<String, Object> matching2 = new HashMap<>();
    static Map<String, Object> matching3 = new HashMap<>();
    static Map<String, Object> matchingApplication = new HashMap<>();
    static Map<String, Object> like = new HashMap<>();
    ObjectMapper objectMapper = new ObjectMapper();
    
    @BeforeAll
    static void beforeAll() {
        matching1.put("writerId", 2L);
        matching1.put("type", MatchingTypeEnum.TravelMate);
        matching1.put("title", "Title1");
        matching1.put("place", "Busan");
        matching1.put("content", "Content1");
        matching1.put("startDate", "2023-09-04T15:00:00Z");
        matching1.put("endDate", "2023-09-07T12:00:00Z");
        matching1.put("maxMember", 6);
        matching1.put("minusAge", 10);
        matching1.put("plusAge", 0);
        matching1.put("readCount", 34);
        matching1.put("isActive", false);
        matching2.put("writerId", 3L);
        matching2.put("type", MatchingTypeEnum.TravelMate);
        matching2.put("title", "Title2");
        matching2.put("place", "Jeju");
        matching2.put("content", "Content2");
        matching2.put("startDate", "2023-09-29T10:00:00Z");
        matching2.put("endDate", "2023-10-06T20:00:00Z");
        matching2.put("maxMember", 5);
        matching2.put("minusAge", 0);
        matching2.put("plusAge", 4);
        matching2.put("readCount", 27);
        matching2.put("isActive", true);
        matching3.put("writerId", 2L);
        matching3.put("type", MatchingTypeEnum.TravelMate);
        matching3.put("title", "Title3");
        matching3.put("place", "Ulsan");
        matching3.put("content", "Content3");
        matching3.put("startDate", "2023-10-01T12:00:00Z");
        matching3.put("endDate", "2023-10-05T16:00:00Z");
        matching3.put("maxMember", 4);
        matching3.put("minusAge", 5);
        matching3.put("plusAge", 5);
        matching3.put("readCount", 10);
        matching3.put("isActive", true);
        matchingApplication.put("userId", 2L);
        matchingApplication.put("matchingId", 2L);
        matchingApplication.put("state", ApplicationStateEnum.Pending);
        matchingApplication.put("isActive", true);
        like.put("userId", 3L);
        like.put("matchingId", 3L);
    }

    @Test
    void createMatchingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/matching")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matching1)))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/matching")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matching2)))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/matching")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matching3)))
                .andExpect(status().isOk());
    }
    
    @Test
    void createMatchingApplicationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/matching/application")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matchingApplication)))
                .andExpect(status().isOk());
    }

    @Test
    void getAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/matching")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getMatchingByIdTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/matching/{matchingId}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPendingUserListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/matching/{matchingId}/pending", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getApprovedUserListTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/matching/{matchingId}/approved", 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getLikeCountTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/matching/{matchingId}/like", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    void updateMatchingTest() throws Exception {
        matching2.replace("readCount", 28);
        mockMvc.perform(MockMvcRequestBuilders.patch("/matching/{matchingId}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(matching2)))
                .andExpect(status().isOk());
    }

    @Test
    void toggleLikeTest() throws Exception {
        matching2.replace("readCount", 28);
        mockMvc.perform(MockMvcRequestBuilders.patch("/matching/like")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(like)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteMatchingTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/matching/{matchingId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/matching")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}