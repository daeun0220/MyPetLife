package com.web.app.repository;

import com.web.app.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    @Query("select b from Board b where b.user.id = :userId")
    List<Board> findAllByUser(@Param("userId") Long userId);
}
