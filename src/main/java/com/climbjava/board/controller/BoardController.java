package com.climbjava.board.controller;

import com.climbjava.board.domain.dto.BoardDTO;
import com.climbjava.board.domain.dto.PageRequestDTO;
import com.climbjava.board.service.BoardService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("board")
@Log4j2
@Data
public class BoardController {
  private final BoardService service;
  @GetMapping("")
  public String index(){
    return "redirect:/board/list";
  }
  //등록 폼
  @GetMapping("register")
  public String register(){
    return "board/register";
  }

  //등록 프로세스
  @PostMapping("register")
  public String register(BoardDTO dto, RedirectAttributes rttr){
    rttr.addFlashAttribute("msg", service.register(dto));
    return"redirect:/board/list";
  }
  //목록 조회
  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO dto, Model model){
    model.addAttribute("dto", service.getList(dto));
  }
  // 게시글 조회
  @GetMapping("read")
  public void read(@ModelAttribute("requestDto") PageRequestDTO dto, Model model, Long bno){
    model.addAttribute("dto",service.get(bno));
  }
  //게시글 수정
  @GetMapping("modify")
  public void modify(@ModelAttribute("pageDto") PageRequestDTO dto, Model model, Long bno){
    model.addAttribute("dto",service.get(bno));
  }

  @PostMapping("modify")
  public String modify(PageRequestDTO dto, BoardDTO boardDto, RedirectAttributes rttr){
    service.modify(boardDto);
    rttr.addAttribute("bno", boardDto.getBno());
    rttr.addAttribute("page", dto.getPage());
    rttr.addAttribute("size", dto.getSize());
    return "redirect:read";
  }

  @PostMapping("remove")
  public String remove(PageRequestDTO dto, Long bno, RedirectAttributes rttr){
    service.remove(bno);
    rttr.addFlashAttribute("msg", bno);
    rttr.addAttribute("page", dto.getPage());
    rttr.addAttribute("size", dto.getSize());
    return "redirect:list";
  }

}
