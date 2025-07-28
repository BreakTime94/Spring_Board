package com.climbjava.board.domain.projection.dto;

import com.climbjava.board.domain.entity.Board;
import com.climbjava.board.domain.entity.Member;

public record BoardWithReplyCountDTO(Board board, Member member, Long replyCount) {
}
