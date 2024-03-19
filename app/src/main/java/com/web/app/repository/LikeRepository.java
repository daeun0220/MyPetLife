package com.web.app.repository;

import com.web.app.domain.Likes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    boolean existsByUserIdAndBoardId(Long boardId, Long userId);
    void deleteByUserIdAndBoardId(Long boardId, Long userId);
    @Query("select l from Likes l where l.user.id = :userId")
    Page<Likes> findAllByUser(@Param("userId") Long userId, Pageable pageable);
}
