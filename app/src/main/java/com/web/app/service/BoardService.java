package com.web.app.service;


import com.web.app.domain.Board;
import com.web.app.domain.User;
import com.web.app.dto.request.BoardRequestDto;
import com.web.app.dto.response.BoardDto;
import com.web.app.dto.response.BoardResponseDto;
import com.web.app.dto.response.ResultType;
import com.web.app.repository.BoardRepository;
import com.web.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    @Transactional
    public Long createBoard(Long userId, BoardRequestDto boardRequestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException(("사용자가 존재하지 않습니다.")));
        Board board = Board.builder()
                .user(user)
                .title(boardRequestDto.getTitle())
                .content(boardRequestDto.getContent())
                .createDate(boardRequestDto.getCreateDate())
                .writer(user.getNickname())
                .build();
        return boardRepository.save(board).getId();
    }
    //개별조회
    public BoardResponseDto searchById(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(("사용자가 존재하지 않습니다.")));
        return BoardResponseDto.from(board);
    }
    @Transactional
    public ResultType updateBoard(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(("사용자가 존재하지 않습니다.")));
        board.update(boardRequestDto);
        return ResultType.SUCCESS;
    }

    public ResultType deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException(("사용자가 존재하지 않습니다.")));
        boardRepository.delete(board);
        return ResultType.SUCCESS;
    }

    public List<BoardResponseDto> searchMyBoard(Long userId) {
        return boardRepository.findAllByUser(userId).stream()
                .map(BoardResponseDto::from)
                .collect(Collectors.toList());
    }

}
