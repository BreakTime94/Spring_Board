package com.climbjava.board.service;

import com.climbjava.board.dto.BoardDTO;
import com.climbjava.board.dto.PageRequestDTO;
import com.climbjava.board.dto.PageResponseDTO;
import com.climbjava.board.entity.Board;
import com.climbjava.board.entity.Member;
import com.climbjava.board.projection.dto.BoardWithReplyCountDTO;

public interface BoardService {
  Long register(BoardDTO boardDTO);

  PageResponseDTO<BoardDTO, BoardWithReplyCountDTO> getList(PageRequestDTO pageRequestDTO);

  BoardDTO get(Long bno);

  void remove(Long bno);

  void modify(BoardDTO boardDTO);

  //DML(insert, update)
  default Board toEntity(BoardDTO dto){
    return Board.builder()
            .bno(dto.getBno())
            .title(dto.getTitle())
            .content(dto.getContent())
            .writer(Member.builder().email(dto.getWriterEmail()).build())
            .build();
  }

  //select
  default BoardDTO toDTO(Board entity, Member member, Long replyCount){
    return BoardDTO.builder()
            .bno(entity.getBno())
            .title(entity.getTitle())
            .content(entity.getContent())
            .regDate(entity.getRegDate())
            .modDate(entity.getModDate())
            .writerEmail(member.getEmail())
            .writerName(member.getName())
            .replyCount(replyCount)
            .build();
  }
  default BoardDTO projectionToDTO(BoardWithReplyCountDTO entity){
    return BoardDTO.builder()
            .bno(entity.board().getBno())
            .title(entity.board().getTitle())
            .content(entity.board().getContent())
            .regDate(entity.board().getRegDate())
            .modDate(entity.board().getModDate())
            .writerEmail(entity.board().getWriter().getEmail())
            .writerName(entity.board().getWriter().getName())
            .replyCount(entity.replyCount())
            .build();
  }
}
