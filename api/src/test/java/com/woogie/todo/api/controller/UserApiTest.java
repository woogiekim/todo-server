package com.woogie.todo.api.controller;

import com.woogie.todo.api.controller.dto.CreateUserReq;
import com.woogie.todo.api.support.BaseApiTest;
import com.woogie.todo.core.user.domain.UserRepository;
import com.woogie.todo.core.user.domain.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserApiTest extends BaseApiTest {

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