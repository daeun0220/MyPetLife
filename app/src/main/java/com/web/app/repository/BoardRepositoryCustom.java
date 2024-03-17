package com.web.app.repository;

import com.web.app.dto.response.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface BoardRepositoryCustom {
    Page<BoardDto> findBySearchOption(Pageable pageable, String title);
}
