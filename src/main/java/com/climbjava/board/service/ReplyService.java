package com.climbjava.board.service;

import com.climbjava.board.domain.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
  Long register(ReplyDTO replyDTO);
  List<ReplyDTO> getList(Long bno);
  ReplyDTO get(Long rno);
  void modify(ReplyDTO replyDTO);
  void delete(Long rno);
}
