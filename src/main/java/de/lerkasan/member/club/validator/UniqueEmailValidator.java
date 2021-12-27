package de.lerkasan.member.club.validator;

import de.lerkasan.member.club.annotation.UniqueEmail;
import de.lerkasan.member.club.repository.MemberRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private MemberRepository memberRepository;

    public UniqueEmailValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Objects.nonNull(value) && memberRepository.isEmailAvailable(value);
    }
}
