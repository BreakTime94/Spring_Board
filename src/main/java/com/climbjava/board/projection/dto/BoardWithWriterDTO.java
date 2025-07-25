package com.climbjava.board.projection.dto;

import com.climbjava.board.entity.Board;
import com.climbjava.board.entity.Member;

public interface BoardWithWriterDTO {
  Board getBoard();
  Member getMember();
}
