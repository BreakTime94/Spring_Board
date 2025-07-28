package com.climbjava.board.repository;

import com.climbjava.board.domain.projection.dto.*;
import com.climbjava.board.domain.entity.Board;
import com.climbjava.board.domain.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTest {
  @Autowired
  private BoardRepository repository;

  @Test
  public void testExist() {
    Assertions.assertNotNull(repository);
  }

  @Test
  public void insertBoardTest(){
    IntStream.range(1, 100).forEach(i -> {
      Member member = Member.builder().email("user" + i + "gmail.con").build();

      Board board = Board.builder()
              .title("title" + i)
              .content("content" + i)
              .writer(member)
              .build();
      repository.save(board);
    });
  }
  @Test
  @Transactional(readOnly = true)
  public void testRead() {
    Board board = repository.findById(3L).orElse(null);
    log.info(board);
    log.info(board.getWriter());
  }

  @Test
  public void testReadWithWriter() {
//    Arrays.stream(repository.getBoardWithWriter(3L)).forEach(o -> {
//      log.info(Arrays.toString((Object[]) o));
//    });
    //Arrays.stream((Object[]) repository.getBoardWithWriter(3L)).forEach(log::info);
    BoardWithWriterDTO dto = repository.getBoardWithWriter(3L);
    log.info(dto.getBoard());
    log.info(dto.getMember());
  }
  @Test
  public void testReadWithWriter2() {
    BoardWithWriterDTORecord dto = repository.getBoardWithWriter2(3L);
    log.info(dto.board());
    log.info(dto.member());
  }

  @Test
  public void testReadWithWriter3() {
    BoardWithWriterDTOClass dto = repository.getBoardWithWriter3(3L);
    log.info(dto.getBoard());
    log.info(dto.getMember());
  }

  @Test
  public void testReadWithReply() {
    //List<Object[]> list = repository.getBoardWithReply(3L);
    //list.stream().forEach(o -> log.info(Arrays.toString(o)));

    List<BoardWithReplyDTORecord> list = repository.getBoardWithReply(3L);

    list.forEach(l -> {
      log.info(l.board());
      log.info(l.reply());
    });
  }
  @Test
  public void testReplyCount(){
    Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
    Page<BoardWithReplyCountDTO> pages = repository.getBoardWithReplyCount(pageable);
    pages.forEach(log::info);
  }

  @Test
  public void testSearch(){
    repository.search1();
  }

  @Test
  public void testSearchPage(){
    Page<BoardWithReplyCountDTO> bwrc = repository.searchPage("tcw", "title", PageRequest.of(3, 5, Sort.by("bno").descending().and(Sort.by(Sort.Direction.ASC, "title"))));

    log.info(bwrc.getContent());
    log.info(bwrc.getTotalElements());
    bwrc.getContent().forEach(log::info);

  }
}
