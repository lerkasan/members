package de.lerkasan.member.club.service;

import de.lerkasan.member.club.exception.NullEntityException;
import de.lerkasan.member.club.model.Member;
import de.lerkasan.member.club.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.lerkasan.member.club.service.MemberServiceImpl.NULL_MEMBER_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    private static Member dummyMember;

    @BeforeEach
    void init(){
        dummyMember = new Member();
        dummyMember.setFirstName("Taras");
        dummyMember.setLastName("Shevchenko");
        dummyMember.setEmail("taras.shevchenko@mail.com");
        dummyMember.setRegistrationTime(LocalDateTime.now());
    }

    @Test
    void createNewMemberTest() {
        Member expected = dummyMember;
        String email = expected.getEmail();
        when(memberRepository.save(expected)).thenReturn(expected);

        Member actual = memberService.create(expected);
        assertEquals(expected, actual);
        verify(memberRepository, times(1)).save(expected);
    }

    @Test
    void createNullUserTest() {
        when(memberRepository.save(null)).thenThrow(new IllegalArgumentException());

        Throwable exception = assertThrows(NullEntityException.class, () -> memberService.create(null));
        assertEquals(NULL_MEMBER_ERROR, exception.getMessage());
        verify(memberRepository, times(1)).save(null);
    }

    @Test
    void getAllMembersTest() {
        Member member1 = new Member();
        Member member2 = new Member();
        List<Member> expected = new ArrayList<>();
        expected.add(member1);
        expected.add(member2);
        when(memberRepository.findAll()).thenReturn(expected);

        List<Member> actual = memberService.getAll();
        assertEquals(expected, actual);
        verify(memberRepository, times(1)).findAll();
    }

    @Test
    void getAllUsersEmptyTest() {
        List<Member> expected = new ArrayList<>();
        when(memberRepository.findAll()).thenReturn(Collections.emptyList());

        List<Member> actual = memberService.getAll();
        assertEquals(expected, actual);
        verify(memberRepository, times(1)).findAll();
    }
}
