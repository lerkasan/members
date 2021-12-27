package de.lerkasan.member.club.validator;

import de.lerkasan.member.club.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UniqueEmailValidatorTests {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private UniqueEmailValidator uniqueEmailValidator;

    @Test
    void validateEmailAvailableTest() {
        String email = "available@mail.com";
        when(memberRepository.isEmailAvailable(email)).thenReturn(true);
        boolean actual = uniqueEmailValidator.isValid(email, context);
        assertTrue(actual);
        verify(memberRepository, times(1)).isEmailAvailable(email);
    }

    @Test
    void validateEmailNotAvailableTest() {
        String email = "registered@mail.com";
        when(memberRepository.isEmailAvailable(email)).thenReturn(false);
        boolean actual = uniqueEmailValidator.isValid(email, context);
        assertFalse(actual);
        verify(memberRepository, times(1)).isEmailAvailable(email);
    }
}
