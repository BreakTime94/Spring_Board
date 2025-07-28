package com.climbjava.board.service;

import com.climbjava.board.domain.dto.ReplyDTO;
import com.climbjava.board.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Log4j2
public class ReplyServiceTest {
  @Autowired
  ReplyService service;

  @Test
  public void testExist() {
    Assertions.assertNotNull(service);
    log.info("service :: {}", service);
  }

  @Test
  public void testInsert(){
    Long result = service.register(ReplyDTO.builder().text("우왕").replyer("USER10").bno(111L).build());

  }

  @Test
  public void testList() {
    service.getList(3L).forEach(log::info);
  }

  @Test
  public void testModify() {
    service.modify(ReplyDTO.builder().rno(301L).text("우왕굿").replyer("게스트").bno(111L).build());
  }

  @Test
  @Transactional
  public void testDelete() {
    service.delete(301L);
  }

  @Test
  public void testGet() {
   //log.info("댓글 한개 :{}", service.get(301L));

   try{
     service.get(1000L);
   } catch (IllegalArgumentException e) {
     log.error(e.getMessage());
   }
  }
}
