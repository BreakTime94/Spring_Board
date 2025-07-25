package com.climbjava.board.repository;

import com.climbjava.board.projection.dto.*;
import com.climbjava.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTO getBoardWithWriter(@Param("bno") Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTORecord getBoardWithWriter2(@Param("bno") Long bno);

  @Query("select b board, w as member from Board b left join b.writer w where b.bno = :bno")
  BoardWithWriterDTOClass getBoardWithWriter3(@Param("bno") Long bno);

  @Query("select b board, r reply from Board b left join Reply r on r.board = b where b.bno = :bno")
  List<BoardWithReplyDTORecord> getBoardWithReply(@Param("bno") Long bno);

  @Query(value = "select b, w, count(r) from Board b left join b.writer w " + "left join Reply r on r.board = b group by b" , countQuery = "select count(b) from Board b")
  Page<BoardWithReplyCountDTO> getBoardWithReplyCount(Pageable pageable);

  @Query(value = "select b, w as member, count(r) replyCount from Board b left join b.writer w " + "left join Reply r on r.board = b where b.bno = :bno")
  BoardWithReplyCountDTO getBoardByBno(Long bno);

}
