package com.ericpinto.desafiovotacaofullstack.domain.vote.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
