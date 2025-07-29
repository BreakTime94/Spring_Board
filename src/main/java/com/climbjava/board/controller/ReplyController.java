package com.climbjava.board.controller;

import com.climbjava.board.domain.dto.ReplyDTO;
import com.climbjava.board.service.ReplyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {
  private final ReplyService replyService;

  //@GetMapping(value ="board/{bno}", produces = "application/json", consumes = {MediaType.APPLICATION_JSON_VALUE}, method=RequestMethod.GET)
  //위의 부분 중에서 produces consumes method의 기본값이 저것이므로 아래 처럼 생략해서 쓸 수 있다.
  @GetMapping(value = "board/{bno}")
  public ResponseEntity<?> getList(@PathVariable("bno") Long bno) {
    log.info("bno: " + bno);
    return ResponseEntity.ok(replyService.getList(bno));
  }

  @PostMapping(value = "")
  public ResponseEntity<?> save(@RequestBody ReplyDTO replyDTO) {
    log.info(replyDTO);
    return ResponseEntity.ok(replyService.register(replyDTO));
  }
}
