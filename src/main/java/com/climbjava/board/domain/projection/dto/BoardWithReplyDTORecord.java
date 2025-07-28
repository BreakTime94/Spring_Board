package com.climbjava.board.domain.projection.dto;

import com.climbjava.board.domain.entity.Board;
import com.climbjava.board.domain.entity.Reply;

public record BoardWithReplyDTORecord(Board board, Reply reply) {
}
