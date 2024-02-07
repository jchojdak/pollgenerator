package com.pollgenerator.exception;

public class AlreadyVotedException extends RuntimeException {
    private final String message;

    public AlreadyVotedException(String message) {
        super(message);
        this.message = message;
    }
}
