package com.web.app.domain;

import com.web.app.dto.request.BoardRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "board")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    private LocalDate createDate;
    private LocalDate modifyDate;
    private String writer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Likes> likeList = new ArrayList<>();
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Images> imageList = new ArrayList<>();
    public void update(BoardRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.modifyDate = dto.getModifyDate();
    }

}
