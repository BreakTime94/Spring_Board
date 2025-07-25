package com.climbjava.board.projection.dto;

import com.climbjava.board.entity.Board;
import com.climbjava.board.entity.Member;

public record BoardWithWriterDTORecord(Board board, Member member) {
}
