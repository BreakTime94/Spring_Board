package com.climbjava.board.repository.search;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Sort;

import java.util.stream.Stream;

public interface SearchSupport<T>  {
  default Stream<OrderSpecifier<?>> getOrder(Class<T> clazz, Sort sort) {
    return sort.stream().map(order -> {
      Order direction = order.isAscending() ? Order.ASC : Order.DESC;
      String prop = order.getProperty();

      Expression<T> expression = new PathBuilder<>(clazz, prop); // prop 이슈 발생 가능성 존재
      return new OrderSpecifier(direction, expression);
    });
  }

  private String toAlias(Class<T> clazz) {
    return Character.toLowerCase(clazz.getName().charAt(0)) + clazz.getName().substring(1);
  }
}
