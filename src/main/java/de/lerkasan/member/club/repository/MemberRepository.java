package de.lerkasan.member.club.repository;

import de.lerkasan.member.club.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT count(m) = 0 FROM Member m WHERE LOWER(m.email) = LOWER(?1)")
    boolean isEmailAvailable(String email);
}
