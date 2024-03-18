package com.web.app.repository;

import com.web.app.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    boolean existsByUserIdAndBoardId(Long boardId, Long userId);
    void deleteByUserIdAndBoardId(Long boardId, Long userId);
}
