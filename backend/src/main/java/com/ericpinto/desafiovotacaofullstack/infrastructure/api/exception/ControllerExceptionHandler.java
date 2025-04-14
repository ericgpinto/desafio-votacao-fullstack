package com.ericpinto.desafiovotacaofullstack.infrastructure.api.exception;

import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.DuplicateVoteException;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.EntityNotFoundException;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.EnumConstantException;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.VoteClosedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleResourceNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CustomError.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(EnumConstantException.class)
    public ResponseEntity<CustomError> handleEnumConstantException(EnumConstantException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CustomError.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler({DuplicateVoteException.class, VoteClosedException.class})
    public ResponseEntity<CustomError> handleBusinessException(DuplicateVoteException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(CustomError.builder()
                        .message(e.getMessage())
                        .build());
    }
}
