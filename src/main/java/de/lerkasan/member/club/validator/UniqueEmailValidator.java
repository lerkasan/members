package de.lerkasan.member.club.validator;

import de.lerkasan.member.club.annotation.UniqueEmail;
import de.lerkasan.member.club.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Objects.isNull(memberRepository) || (Objects.nonNull(value) && memberRepository.isEmailAvailable(value));
    }
}
