package com.web.app.controller;

import com.web.app.dto.request.BoardRequestDto;
import com.web.app.dto.response.BoardResponseDto;
import com.web.app.dto.response.ResultType;
import com.web.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypetlife")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board/{userId}")
    public Long createBoard(@PathVariable Long userId, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(userId, boardRequestDto);
    }

    @GetMapping("/board/{boardId}")
    public BoardResponseDto searchById(@PathVariable Long boardId) {
        return boardService.searchById(boardId);
    }
    @PatchMapping("/board/{boardId}")
    public ResultType updateBoard(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.updateBoard(boardId, boardRequestDto);
    }
    @DeleteMapping("/board/{boardId}")
    public ResultType deleteBoard(@PathVariable Long boardId) {
        return boardService.deleteBoard(boardId);
    }
}
