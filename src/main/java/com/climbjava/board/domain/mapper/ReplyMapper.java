package com.climbjava.board.domain.mapper;

import com.climbjava.board.domain.dto.ReplyDTO;
import com.climbjava.board.domain.entity.Reply;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ReplyMapper {
  @Mapping(source = "board.bno", target = "bno")
  ReplyDTO toDto(Reply reply);
  @InheritInverseConfiguration
  Reply toEntity(ReplyDTO replyDTO);
}
