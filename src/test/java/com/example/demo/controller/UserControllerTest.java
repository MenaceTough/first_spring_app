package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com.example.demo.dto.UserDtoTest.userDtoTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {
    UserService userService;
    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void saveUser() throws Exception {
        when(userService.saveUser(any())).thenReturn(userDtoTest());
        mockMvc.perform(post("/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDtoTest())))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) content().json(objectMapper.writeValueAsString(userDtoTest())));
    }

    @Test
    void findAllUsers() throws Exception {
        when(userService.findAll()).thenReturn(Collections.singletonList(userDtoTest()));
        mockMvc.perform(get("/users/findAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) content().json(objectMapper.writeValueAsString(Collections.singletonList(userDtoTest()))))
                .andExpect(status().isOk());
    }

    @Test
    void findByLogin() {
    }

    @Test
    void deleteUsers() {
    }

}
