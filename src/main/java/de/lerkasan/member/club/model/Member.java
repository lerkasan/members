package de.lerkasan.member.club.model;

import de.lerkasan.member.club.annotation.UniqueEmail;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    @Pattern(regexp = "\\b([A-Z][a-z]+[-. ']{0,1}+[A-Za-z ]+[ .]{0,1})+$",
            message = "First name must start with a capital letter followed by lowercase letters, dot, space, hyphen, apostrophe. First name should be at least three characters long.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "\\b([A-Z][a-z]+[-. ']{0,1}+[A-Za-z ]+[ .]{0,1})+$",
            message = "Last name must start with a capital letter followed by lowercase letters, dot, space, hyphen, apostrophe. " +
                    "Last name should be at least three characters long.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "Please enter valid email.")
    @NotEmpty(message = "Please enter valid email.")
    @UniqueEmail
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "registration_time", nullable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP(0)")
    private LocalDateTime registrationTime = LocalDateTime.now();

    public Member() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Member member = (Member) o;
        return id == member.id && Objects.equals(firstName, member.firstName) && Objects.equals(lastName, member.lastName) &&
                Objects.equals(email, member.email) && Objects.equals(registrationTime, member.registrationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, registrationTime);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", registrationTime=" + registrationTime +
                '}';
    }
}
