package com.web.app.controller;

import com.web.app.dto.request.BoardRequestDto;
import com.web.app.dto.response.BoardDto;
import com.web.app.dto.response.BoardResponseDto;
import com.web.app.dto.response.ResultType;
import com.web.app.repository.BoardRepository;
import com.web.app.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mypetlife")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    @PostMapping("/board/{userId}")
    public Long createBoard(@PathVariable Long userId, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createBoard(userId, boardRequestDto);
    }
    @GetMapping("/myboard/{userId}")
    public List<BoardResponseDto> searchMyBoard(@PathVariable Long userId) {
        return boardService.searchMyBoard(userId);
    }
    @GetMapping("/board/{boardId}")
    public BoardResponseDto searchById(@PathVariable Long boardId) {
        return boardService.searchById(boardId);
    }
    @GetMapping("/board")
    public Page<BoardDto> searchAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                    @RequestParam(name = "size", required = false, defaultValue = "10") int size,
                                    @RequestParam(required = false) String title) {
        Pageable pageable = PageRequest.of(page, size);
        return boardRepository.findBySearchOption(pageable, title);
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
