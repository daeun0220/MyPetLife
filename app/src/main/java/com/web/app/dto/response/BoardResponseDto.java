package com.web.app.dto.response;

import com.web.app.domain.Board;
import com.web.app.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDto {
    private String title;
    private String content;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private String writer;
    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .modifyDate(board.getModifyDate())
                .writer(board.getWriter())
                .build();
    }

}
