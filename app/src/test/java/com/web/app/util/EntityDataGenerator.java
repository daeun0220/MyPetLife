package com.web.app.util;

import com.web.app.domain.Board;
import com.web.app.domain.User;
import com.web.app.dto.request.BoardRequestDto;

import java.time.LocalDate;

public class EntityDataGenerator {
    public static Board board(Long boardId, BoardRequestDto dto) {
        return Board.builder()
                .id(boardId)
                .title(dto.getTitle())
                .content(dto.getContent())
                .createDate(dto.getCreateDate())
                .build();
    }
    public static Board board(Long boardId) {
        return Board.builder()
                .id(boardId)
                .title("테스트")
                .content("테스트 내용입니다")
                .createDate(LocalDate.of(2024, 3, 15))
                .build();
    }
    public static User user(Long userId) {
        return User.builder()
                .id(userId)
                .email("test1")
                .nickname("daeun")
                .build();
    }
}
