package com.web.app.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import com.web.app.domain.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
public class BoardDto {
    private String title;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private String writer;
    public static BoardDto from(Board board) {
        return BoardDto.builder()
                .title(board.getTitle())
                .createDate(board.getCreateDate())
                .modifyDate(board.getModifyDate())
                .writer(board.getWriter())
                .build();
    }
    @QueryProjection
    public BoardDto(String title, LocalDate createDate, LocalDate modifyDate, String wrtier) {
        this.title = title;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.writer = wrtier;
    }
}
