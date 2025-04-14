package com.ericpinto.desafiovotacaofullstack.domain.vote.exception;

public class VoteClosedException extends RuntimeException {
    public VoteClosedException(String message) {
        super(message);
    }
}
