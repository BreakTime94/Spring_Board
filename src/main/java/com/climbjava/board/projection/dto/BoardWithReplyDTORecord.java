package com.climbjava.board.projection.dto;

import com.climbjava.board.entity.Board;
import com.climbjava.board.entity.Reply;

public record BoardWithReplyDTORecord(Board board, Reply reply) {
}
