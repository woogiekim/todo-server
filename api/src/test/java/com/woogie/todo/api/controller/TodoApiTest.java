package com.woogie.todo.api.controller;

import com.woogie.todo.api.controller.dto.CreateTodoReq;
import com.woogie.todo.api.support.BaseApiTest;
import com.woogie.todo.core.todo.domain.TodoRepository;
import com.woogie.todo.core.todo.domain.TodoStatus;
import com.woogie.todo.core.user.domain.User;
import com.woogie.todo.core.user.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TodoApiTest extends BaseApiTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void 투두생성() throws Exception {
        var user = new User("wook@naver.com", "김태욱", "1234");
        userRepository.save(user);

        var now = OffsetDateTime.now();
        var req = new CreateTodoReq(user.getId(), "title", "description", now, now);

        mockMvc.perform(post("/todo")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(req))
               )
               .andExpect(status().isOk());

        var foundTodo = todoRepository.findAllByUserId(user.getId()).stream().findFirst().orElseThrow();

        assertThat(foundTodo.getTitle()).isEqualTo("title");
        assertThat(foundTodo.getDescription()).isEqualTo("description");
        assertThat(foundTodo.getStartTime()).isEqualToIgnoringSeconds(now);
        assertThat(foundTodo.getEndTime()).isEqualToIgnoringSeconds(now);
        assertThat(foundTodo.getStatus()).isEqualTo(TodoStatus.IN_PROGRESS);
    }
}