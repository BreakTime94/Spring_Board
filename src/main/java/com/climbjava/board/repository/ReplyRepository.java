package com.climbjava.board.repository;

import com.climbjava.board.entity.Board;
import com.climbjava.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

  void deleteByBoard_Bno(Long bno);

}
