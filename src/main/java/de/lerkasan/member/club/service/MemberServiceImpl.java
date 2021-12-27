package de.lerkasan.member.club.service;

import de.lerkasan.member.club.exception.NullEntityException;
import de.lerkasan.member.club.model.Member;
import de.lerkasan.member.club.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    public static final String NULL_MEMBER_ERROR = "Member cannot be 'null'";

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member create(Member member) {
        try {
            return memberRepository.save(member);
        } catch (IllegalArgumentException e) {
            throw new NullEntityException(NULL_MEMBER_ERROR);
        }
    }

    @Override
    public List<Member> getAll() {
        List<Member> members = memberRepository.findAll();
        return members.isEmpty() ? new ArrayList<>() : members;
    }
}
