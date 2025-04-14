package com.ericpinto.desafiovotacaofullstack.domain.vote.exception;

public class DuplicateVoteException extends RuntimeException {
    public DuplicateVoteException(String message) {
        super(message);
    }
}
