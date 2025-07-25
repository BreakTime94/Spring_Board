package com.climbjava.board.projection.dto;

import com.climbjava.board.entity.Board;
import com.climbjava.board.entity.Member;
import com.climbjava.board.entity.Reply;

public record BoardWithReplyCountDTO(Board board, Member member, Long replyCount) {
}
