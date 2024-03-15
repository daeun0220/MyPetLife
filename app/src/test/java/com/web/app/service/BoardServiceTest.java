package com.web.app.service;

import com.web.app.domain.Board;
import com.web.app.domain.User;
import com.web.app.dto.request.BoardRequestDto;
import com.web.app.repository.BoardRepository;
import com.web.app.repository.UserRepository;
import com.web.app.util.EntityDataGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
public class BoardServiceTest {
    @Mock
    BoardRepository boardRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    BoardService boardService;
    static BoardRequestDto boardDto;

    @Test
    void test_create_board() {
        User user = EntityDataGenerator.user(1L);
        Board board = EntityDataGenerator.board(1L);
        when(userRepository.findById(eq(1L))).thenReturn(Optional.of(user));
        when(boardRepository.save(any(Board.class))).thenReturn(board);

        boardService.createBoard(user.getId(), BoardRequestDto.builder()
                .title("테스트")
                .content("내용")
                .createDate(LocalDate.of(2024, 3, 15))
                .build());

        verify(boardRepository).save(argThat(boardt -> boardt.getTitle().equals("테스트")));
    }

}
