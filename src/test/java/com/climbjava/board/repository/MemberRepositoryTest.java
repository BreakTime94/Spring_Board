package com.climbjava.board.repository;

import com.climbjava.board.entity.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTest {
  @Autowired
  MemberRepository repository;

  @Test
  public void testExist() {
    Assertions.assertNotNull(repository);
  }

  @Test
  public void insertMember() {
    IntStream.range(1, 100).forEach(i -> {
      Member member = Member.builder()
              .email("user" + i + "gmail.con")
              .password("1111")
              .name("USER" + i)
              .build();
      repository.save(member);
    });
  }
}
