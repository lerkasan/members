package de.lerkasan.member.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Sql("/test-data.sql")
    public void isEmailAvailableTest(){
        boolean actual = memberRepository.isEmailAvailable("available@mail.com");
        assertTrue(actual);
    }

    @Test
    @Sql("/test-data.sql")
    public void isEmailUnavailableTest(){
        boolean actual = memberRepository.isEmailAvailable("unavailable@mail.com");
        assertFalse(actual);
    }
}
