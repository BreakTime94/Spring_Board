package com.climbjava.board.service;

import com.climbjava.board.dto.BoardDTO;
import com.climbjava.board.dto.PageRequestDTO;
import com.climbjava.board.dto.PageResponseDTO;
import com.climbjava.board.entity.Board;
import com.climbjava.board.projection.dto.BoardWithReplyCountDTO;
import com.climbjava.board.repository.BoardRepository;
import com.climbjava.board.repository.MemberRepository;
import com.climbjava.board.repository.ReplyRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@Data
public class BoardServiceImpl implements BoardService {
  @Autowired
  BoardRepository boardRepository;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  ReplyRepository replyRepository;
  @Override
  public Long register(BoardDTO boardDTO) {
    return boardRepository.save(toEntity(boardDTO)).getBno();
  }

  @Override
  public PageResponseDTO<BoardDTO, BoardWithReplyCountDTO> getList(PageRequestDTO pageRequestDTO) {
    return new PageResponseDTO<>(boardRepository.getBoardWithReplyCount(PageRequest.of(pageRequestDTO.getPage() - 1, 10, Sort.by(Sort.Direction.DESC,"bno")))
            , bwrc -> toDTO(bwrc.board(), bwrc.member(), bwrc.replyCount()));

    // 뒤쪽 부분은 this::projectionToDTO 로도 처리 가능
  }

  @Override
  public BoardDTO get(Long bno) {
    return projectionToDTO(boardRepository.getBoardByBno(bno));
  }

  @Transactional
  @Override
  public void remove(Long bno) {
    replyRepository.deleteByBoard_Bno(bno);
    boardRepository.deleteById(bno);
  }

  public void modify(BoardDTO boardDTO) {
    Board board = boardRepository.findById(boardDTO.getBno()).orElseThrow(() -> new IllegalArgumentException("해당 게시글은 없소이다."));

    board.changeTitle(boardDTO.getTitle());
    board.changeContent(boardDTO.getContent());

    boardRepository.save(board);
  }

}
