package com.climbjava.board.repository;

import com.climbjava.board.entity.Board;
import com.climbjava.board.entity.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
  @Autowired
  ReplyRepository repository;
  @Autowired
  BoardRepository boardRepository;

  @Test
  public void testExist() {
    Assertions.assertNotNull(repository);
  }
  @Test
  public void insertReplyTest(){
    List<Long> bnos = boardRepository.findAll().stream().map(Board::getBno).toList();

    IntStream.rangeClosed(1, 300).forEach(i -> {
      long bno = bnos.get(new Random().nextInt(bnos.size()));
      Board board = Board.builder().bno(bno).build();
      Reply reply = Reply.builder()
              .board(board)
              .text("reply" + i)
              .replyer("guest")
              .build();
      repository.save(reply);
    });
  }
  @Test
  public void testRead() {
    Reply reply = repository.findById(1L).get();
    log.info(reply);
    log.info(reply.getBoard());
    log.info(reply.getBoard().getWriter());
  }
}
