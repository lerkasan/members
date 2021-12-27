package de.lerkasan.member.club.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberTests {

    @Autowired
    private Validator validator;

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
    void validateValidMember() {
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideValidFirstName")
    void validateValidFirstName(String input) {
        dummyMember.setFirstName(input);
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFirstName")
    void validateInvalidFirstName(String input) {
        dummyMember.setFirstName(input);
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
    }

    @Test
    void validateEmptyFirstName() {
        dummyMember.setFirstName("");
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(2, violations.size());
        assertEquals("", violations.iterator().next().getInvalidValue());
    }

    @ParameterizedTest
    @MethodSource("provideValidLastName")
    void validateValidLastName(String input) {
        dummyMember.setFirstName(input);
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLastName")
    void validateInvalidLastName(String input) {
        dummyMember.setFirstName(input);
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
    }

    @Test
    void validateEmptyLastName() {
        dummyMember.setLastName("");
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(2, violations.size());
        assertEquals("", violations.iterator().next().getInvalidValue());
    }

    @ParameterizedTest
    @MethodSource("provideValidEmail")
    void validateValidEmail(String input) {
        dummyMember.setEmail(input);
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEmail")
    void validateInvalidEmail(String input) {
        dummyMember.setEmail(input);
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
    }

    @Test
    @Sql("/test-data.sql")
    void validateEmailAvailableTest() {
        dummyMember.setEmail("available@mail.com");
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideUnavailableEmail")
    @Sql("/test-data.sql")
    void validateEmailUnavailableTest(String input) {
        dummyMember.setEmail(input);
        Set<ConstraintViolation<Member>> violations = validator.validate(dummyMember);
        assertEquals(1, violations.size());
        assertEquals(input, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidName(){
        return Stream.of(
                Arguments.of("invalid"),
                Arguments.of("Invalid-"),
                Arguments.of("Invalid352invalid"),
                Arguments.of("Invalid--invalid"),
                Arguments.of("Invalid@invalid"),
                Arguments.of("Invalid  invalid")
        );
    }

    private static Stream<Arguments> provideValidFirstName(){
        return Stream.of(
                Arguments.of("Lucas"),
                Arguments.of("Amy"),
                Arguments.of("Michael Randolph"),
                Arguments.of("Mary-Anna"),
                Arguments.of("Miss Jane"),
                Arguments.of("Mrs. Jane"),
                Arguments.of("Mr. James"),
                Arguments.of("Roby J.")
        );
    }

    private static Stream<Arguments> provideInvalidFirstName(){
        return Stream.of(
                Arguments.of("L"),
                Arguments.of("lucas"),
                Arguments.of("Leonard-"),
                Arguments.of("Mike352"),
                Arguments.of("Mike352Joe"),
                Arguments.of("Mary--Anna"),
                Arguments.of("David@Tom"),
                Arguments.of("David@mail.com"),
                Arguments.of("12345"),
                Arguments.of("Nick 12345"),
                Arguments.of("Nick %"),
                Arguments.of(" ")
        );
    }

    private static Stream<Arguments> provideValidLastName(){
        return Stream.of(
                Arguments.of("Stevenson"),
                Arguments.of("Peters"),
                Arguments.of("Johnson"),
                Arguments.of("Mc Donald"),
                Arguments.of("Howard-Bryson"),
                Arguments.of("Lee Jr."),
                Arguments.of("Mr. James")
        );
    }

    private static Stream<Arguments> provideInvalidLastName(){
        return Stream.of(
                Arguments.of("L"),
                Arguments.of("peterson"),
                Arguments.of("Johnson-"),
                Arguments.of("Mozart352"),
                Arguments.of("Mozart352Tarantino"),
                Arguments.of("Rodrigez--Robertson"),
                Arguments.of("Davidson@Tompson"),
                Arguments.of("Davidson@mail.com"),
                Arguments.of("12345 Jackson"),
                Arguments.of(" ")
        );
    }

    private static Stream<Arguments> provideValidEmail(){
        return Stream.of(
                Arguments.of("example@mail.com"),
                Arguments.of("email@email.ua"),
                Arguments.of("email123@com.org"),
                Arguments.of("123email@ukr.net"),
                Arguments.of("123-email@kiev.com.ua"),
                Arguments.of("office.email@google.com")
        );
    }

    private static Stream<Arguments> provideInvalidEmail(){
        return Stream.of(
                Arguments.of("invalidEmail.com"),
                Arguments.of("email@"),
                Arguments.of("email@com-"),
                Arguments.of("email-ua"),
                Arguments.of("email@com."),
                Arguments.of("email@."),
                Arguments.of("email."),
                Arguments.of("invalid"),
                Arguments.of("@"),
                Arguments.of(" "),
                Arguments.of("")
        );
    }

    private static Stream<Arguments> provideUnavailableEmail(){
        return Stream.of(
                Arguments.of("unavailable@mail.com"),
                Arguments.of("UNAVAILABLE@MAIL.COM"),
                Arguments.of("unAvaiLabLe@mAiL.Com")
        );
    }
}
