package com.web.app.controller;

import com.web.app.dto.response.BoardDto;
import com.web.app.dto.response.ResultType;
import com.web.app.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mypetlife")
@RequiredArgsConstructor
@Slf4j
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/like/{boardId}/user/{userId}")
    public ResultType setWish(@PathVariable Long boardId, @PathVariable Long userId) {
        likeService.like(boardId, userId);
        return ResultType.SUCCESS;
    }
    @GetMapping("/like/{userId}")
    public List<BoardDto> searchLike(@PathVariable Long userId,
                                     @RequestParam(name = "page", required = false, defaultValue = "0") int page,
                                     @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        return likeService.searchLike(userId, page, size);
    }
}
