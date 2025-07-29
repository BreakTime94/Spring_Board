package com.climbjava.board.service;

import com.climbjava.board.domain.dto.ReplyDTO;
import com.climbjava.board.domain.mapper.ReplyMapper;
import com.climbjava.board.repository.ReplyRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
public class ReplyServiceImpl implements ReplyService {
  private final ReplyMapper mapper;
  private final ReplyRepository repository;

  @Override
  public Long register(ReplyDTO replyDTO) {
    return repository.save(mapper.toEntity(replyDTO)).getRno();
  }

  @Override
  public List<ReplyDTO> getList(Long bno) {
    return repository.findByBoard_BnoOrderByRno(bno).stream().map(mapper::toDto).toList();
  }

  @Override
  @Transactional
  public void modify(ReplyDTO replyDTO) {
    repository.save(mapper.toEntity(replyDTO));
  }

  @Override
  public void delete(Long rno) {
    repository.deleteById(rno);
  }

  @Override
  public ReplyDTO get(Long rno) {
    return mapper.toDto(repository.findById(rno).orElse(null));
  }
}
