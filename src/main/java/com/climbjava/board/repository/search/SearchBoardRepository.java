package com.climbjava.board.repository.search;

import com.climbjava.board.domain.entity.Board;
import com.climbjava.board.domain.projection.dto.BoardWithReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository extends SearchSupport<Board> {
  Board search1();

  Page<BoardWithReplyCountDTO> searchPage (String type, String keyword, Pageable pageable);
}
