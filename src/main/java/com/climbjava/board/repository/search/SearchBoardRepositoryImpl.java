package com.climbjava.board.repository.search;

import com.climbjava.board.domain.entity.Board;
import com.climbjava.board.domain.entity.QBoard;
import com.climbjava.board.domain.entity.QMember;
import com.climbjava.board.domain.entity.QReply;
import com.climbjava.board.domain.projection.dto.BoardWithReplyCountDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

  public SearchBoardRepositoryImpl() {
    super(Board.class);
  }

  @Override
  public Board search1() {
    log.info("==================================================");
    QBoard qBoard = QBoard.board;
    JPQLQuery<Board> jpqlQuery = from(qBoard);
    jpqlQuery.where(qBoard.bno.gt(0L));
    log.info(jpqlQuery);

    //Member, reply join
    jpqlQuery
            .leftJoin(QMember.member).on(qBoard.writer.eq(QMember.member))
            .leftJoin(QReply.reply).on(QReply.reply.board.eq(qBoard));

    JPQLQuery<Tuple> tuple = jpqlQuery.select(qBoard, QMember.member, QReply.reply.count()).groupBy(qBoard).limit(10);
    log.info("fetch 전 {}", tuple);
    List<Tuple> list = tuple.fetch();
    log.info("fetch 후 :: {}", list);
    log.info("==================================================");
    return null;
  }

  @Override
  public Page<BoardWithReplyCountDTO> searchPage(String type, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QReply reply = QReply.reply;
    QMember member = QMember.member;

    //조인
    JPQLQuery<Board> jpqlQuery = from(board);

    jpqlQuery
            .leftJoin(member).on(board.writer.eq(member))
            .leftJoin(reply).on(reply.board.eq(board));

    JPQLQuery<Tuple> tuple = jpqlQuery.groupBy(board).select(board, member, reply.count());

    //검색
    BooleanBuilder builder = new BooleanBuilder();
    BooleanExpression expression = board.bno.gt(0L);
    builder.and(expression);

    if(!(type == null || type.trim().isEmpty())) {
      BooleanBuilder condition = new BooleanBuilder();

      if(type.contains("t")){
        condition.or(board.title.contains(keyword));
      }
      if(type.contains("c")){
        condition.or(board.content.contains(keyword));
      }
      if(type.contains("w")){
        condition.or(member.name.contains(keyword));
      }
      builder.and(condition);
    }
    tuple.where(builder);
    //tuple orderby 적용
    getOrder(Board.class, pageable.getSort()).forEach(tuple :: orderBy);

    //페이지 적용
    tuple.limit(pageable.getPageSize());
    tuple.offset(pageable.getOffset());
//    List<?> list = tuple.fetch();
//    list.forEach(log :: info);

    //DTO Projection 변환
    JPQLQuery<BoardWithReplyCountDTO> query = tuple.select(Projections.constructor(BoardWithReplyCountDTO.class, board, member, reply.count()));

    //page 타입 반환
    return new PageImpl<>(query.fetch(), pageable, tuple.fetchCount());
  }
}
