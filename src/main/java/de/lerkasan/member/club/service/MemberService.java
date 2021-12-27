package de.lerkasan.member.club.service;

import de.lerkasan.member.club.model.Member;

import java.util.List;

public interface MemberService {
    Member create(Member member);
    List<Member> getAll();
}
