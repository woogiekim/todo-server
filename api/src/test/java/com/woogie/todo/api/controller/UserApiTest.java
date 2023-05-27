package com.woogie.todo.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woogie.todo.api.controller.dto.CreateUserReq;
import com.woogie.todo.core.user.domain.UserRepository;
import com.woogie.todo.core.user.domain.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest
class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 유저생성() throws Exception {
        var req = new CreateUserReq("wook@naver.com", "name", "password");

        mockMvc.perform(post("/users")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(req))
               )
               .andExpect(status().isOk());

        var foundUser = userRepository.findAll().stream().findFirst().orElseThrow();

        assertThat(foundUser.getEmail()).isEqualTo("wook@naver.com");
        assertThat(foundUser.getName()).isEqualTo("name");
        assertThat(foundUser.getPassword()).isEqualTo("password");
        assertThat(foundUser.getStatus()).isEqualTo(UserStatus.ACTIVATED);
    }
}