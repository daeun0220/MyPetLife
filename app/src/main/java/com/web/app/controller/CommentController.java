package com.web.app.controller;

import com.web.app.dto.request.CommentCreateRequestDto;
import com.web.app.dto.response.CommentDto;
import com.web.app.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypetlife")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comments")
    public CommentDto createComment(@RequestBody CommentCreateRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }
    @GetMapping(value = "/comments/{boardId}")
    public List<CommentDto> findAllCommentsByTicketId(@PathVariable("boardId") Long boardId) {
        return commentService.findCommentsByBoardId(boardId);
    }

}
