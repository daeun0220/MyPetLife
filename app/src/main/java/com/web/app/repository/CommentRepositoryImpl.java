package com.web.app.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.app.domain.Comment;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.web.app.domain.QComment.comment;

public class CommentRepositoryImpl implements CommentRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public CommentRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Comment> findByBoardId(Long boardId) {
        return queryFactory.selectFrom(comment)
                .leftJoin(comment.parent)
                .fetchJoin()
                .where(comment.board.id.eq(boardId))
                .orderBy(
                        comment.parent.id.asc().nullsFirst(),
                        comment.createDate.asc()
                ).fetch();
    }
}
