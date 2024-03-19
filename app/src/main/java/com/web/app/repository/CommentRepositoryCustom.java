package com.web.app.repository;

import com.web.app.domain.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<Comment> findByBoardId(Long boardId);
}
