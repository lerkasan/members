package de.lerkasan.member.club.exception;

public class NullEntityException extends RuntimeException {
    public NullEntityException() {    }

    public NullEntityException(String message) {
        super(message);
    }
}
