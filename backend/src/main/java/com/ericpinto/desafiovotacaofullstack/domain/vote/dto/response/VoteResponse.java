package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "Modelo que representa o objeto de retorno ao votar em uma pauta")
public record VoteResponse(
        @Schema(description = "Voto do associado", example = "YES")
        String vote,
        @Schema(description = "Horário da votação")
        LocalDateTime createdAt,
        @Schema(description = "Dados do associado")
        AssociateResponse associate
) {
}
