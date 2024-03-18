package com.web.app.controller;

import com.web.app.dto.response.ResultType;
import com.web.app.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
