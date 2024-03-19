package com.web.app.dto.response;

import com.web.app.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private String content;
    private Long userId;
    private String nickname;
    private List<CommentDto> children = new ArrayList<>();

    public CommentDto(Long id, String content, Long userId, String nickname) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.nickname = nickname;
    }

    public static CommentDto convertCommentToDto(Comment comment) {
        return comment.isDeleted() ?
                new CommentDto(comment.getId(), "삭제된 댓글입니다.", null, null) :
                new CommentDto(comment.getId(), comment.getContent(), comment.getUser().getId(), comment.getUser().getNickname());
    }
}