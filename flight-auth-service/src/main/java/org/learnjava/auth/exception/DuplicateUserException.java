package org.learnjava.auth.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("Email or username already exists");
    }
}
