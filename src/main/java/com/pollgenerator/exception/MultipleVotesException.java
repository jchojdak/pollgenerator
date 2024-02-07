package com.pollgenerator.exception;

public class MultipleVotesException extends RuntimeException {
    private final String message;

    public MultipleVotesException(String message) {
        super(message);
        this.message = message;
    }
}
