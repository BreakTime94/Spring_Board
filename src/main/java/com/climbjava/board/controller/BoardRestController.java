package com.climbjava.board.controller;

import com.climbjava.board.domain.dto.BoardDTO;
import com.climbjava.board.domain.dto.PageRequestDTO;
import com.climbjava.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("boardrest")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class BoardRestController {
  private final BoardService boardService;

  @GetMapping(value = "list")
  public ResponseEntity<?> getList(@ModelAttribute("requestDto")  PageRequestDTO dto) {
    return ResponseEntity.ok(boardService.getList(dto));
  }

  @GetMapping( "read/{bno}")
  public ResponseEntity<?> getBoard(@ModelAttribute("requestDto")  PageRequestDTO dto, @PathVariable("bno") Long bno) {
    log.info("bno: " + bno);
    return ResponseEntity.ok(boardService.get(bno));
  }

  @PostMapping("register")
  public ResponseEntity<?> register(@RequestBody BoardDTO dto) {
    log.info("dto: " + dto);
    return ResponseEntity.ok(dto.getBno());
  }

  @PostMapping("modify")
  public ResponseEntity<?> modify(@RequestBody(required = false) BoardDTO dto) {
    boardService.modify(dto);
    return ResponseEntity.ok(200);
  }

  @PostMapping("remove")
  public ResponseEntity<?> remove(Long bno) {
    boardService.remove(bno);
    return ResponseEntity.ok("글이 삭제되었습니다.");
  }

}
