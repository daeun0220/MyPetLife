package com.web.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.app.domain.Board;
import com.web.app.dto.request.BoardRequestDto;
import com.web.app.service.BoardService;
import com.web.app.util.EntityDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BoardControllerTest {
    @Nested
    @WebMvcTest(controllers = BoardController.class)
    @AutoConfigureMockMvc(addFilters = false)
    class BoardApiTest{
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper objectMapper;
        @MockBean
        private BoardService boardService;
        @DisplayName("POST : /mypetlife/board/{userId}")
        @Test
        void test_create_board() throws Exception {
            Long userId = 1L;
            Long boardId = 1L;
            BoardRequestDto request = BoardRequestDto.builder()
                    .title("제목")
                    .content("내용입니다")
                    .createDate(LocalDate.of(2024, 3, 15))
                    .build();

            String requestStr = objectMapper.writeValueAsString(request);
            Board board = EntityDataGenerator.board(boardId, request);

            mockMvc.perform(post("/mypetlife/board/1")
                            .content(requestStr)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }

    }
}
