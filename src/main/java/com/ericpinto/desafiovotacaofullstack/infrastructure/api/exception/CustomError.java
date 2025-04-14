package com.ericpinto.desafiovotacaofullstack.infrastructure.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomError {

    private String message;
}
