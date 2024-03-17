package com.web.app.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.app.dto.response.BoardDto;
import com.web.app.dto.response.QBoardDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.web.app.domain.QBoard.board;
import static org.springframework.util.StringUtils.hasText;

public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Page<BoardDto> findBySearchOption(Pageable pageable, String title) {
        QueryResults<BoardDto> results =  queryFactory
                .select(new QBoardDto(
                        board.title,
                        board.createDate,
                        board.modifyDate,
                        board.writer
                ))
                .from(board)
                .where(
                        titleEq(title)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<BoardDto> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression titleEq(String title) {
        return hasText(title) ? board.title.eq(title) : null;
    }
}

