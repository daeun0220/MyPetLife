package com.web.app.service;

import com.web.app.domain.Board;
import com.web.app.domain.Likes;
import com.web.app.domain.User;
import com.web.app.dto.response.BoardDto;
import com.web.app.repository.BoardRepository;
import com.web.app.repository.LikeRepository;
import com.web.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    public void like(Long boardId, Long userId) {
        if (likeRepository.existsByUserIdAndBoardId(boardId, userId)) {
            likeRepository.deleteByUserIdAndBoardId(boardId, userId);
        } else {
            User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(("사용자가 존재하지 않습니다.")));
            Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(("게시판이 존재하지 않습니다.")));
            Likes like = Likes.builder()
                    .user(user)
                    .board(board)
                    .build();
            likeRepository.save(like);
        }
    }

    public List<BoardDto> searchLike(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Likes> likes = likeRepository.findAllByUser(userId, pageable).toList();
        List<BoardDto> likeList = likes.stream()
                .map(this::convertLikeList)
                .collect(Collectors.toList());
        return likeList;
    }
    private BoardDto convertLikeList(Likes likes) {
        Long boardId = likes.getBoard().getId();
        Optional<Board> board = boardRepository.findById(boardId);
        if (board.isEmpty()) {
            return null;
        }
        return BoardDto.from(board.get());

    }

}
