package com.web.app.service;

import com.web.app.domain.Comment;
import com.web.app.domain.User;
import com.web.app.dto.request.CommentCreateRequestDto;
import com.web.app.dto.response.CommentDto;
import com.web.app.repository.BoardRepository;
import com.web.app.repository.CommentRepository;
import com.web.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentDto createComment(CommentCreateRequestDto requestDto) {
        Comment comment = commentRepository.save(
                Comment.createComment(requestDto.getContent(),
                        boardRepository.findById(requestDto.getBoardId()).orElseThrow(() -> new IllegalArgumentException(("게시판이 존재하지 않습니다."))),
                        userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException(("사용자가 존재하지 않습니다."))),
                        requestDto.getParentId() != null ?
                                commentRepository.findById(requestDto.getParentId()).orElseThrow(() -> new IllegalArgumentException(("댓글이 존재하지 않습니다."))) : null)
        );
        return CommentDto.convertCommentToDto(comment);
    }

    public List<CommentDto> findCommentsByBoardId(Long boardId) {
        boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(("게시판이 존재하지 않습니다.")));
        return convertNestedStructure(commentRepository.findByBoardId(boardId));
    }

    private List<CommentDto> convertNestedStructure(List<Comment> comments) {
        List<CommentDto> result = new ArrayList<>();
        Map<Long, CommentDto> map = new HashMap<>();
        comments.stream().forEach(c -> {
            CommentDto dto = CommentDto.convertCommentToDto(c);
            map.put(dto.getId(), dto);
            if(c.getParent() != null) map.get(c.getParent().getId()).getChildren().add(dto);
            else result.add(dto);
        });
        return result;
    }
}
