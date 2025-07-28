package com.climbjava.board.domain.projection.dto;

import com.climbjava.board.domain.entity.Board;
import com.climbjava.board.domain.entity.Member;

public interface BoardWithWriterDTO {
  Board getBoard();
  Member getMember();
}
