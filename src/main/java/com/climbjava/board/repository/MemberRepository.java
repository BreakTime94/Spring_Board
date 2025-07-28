package com.climbjava.board.repository;

import com.climbjava.board.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
