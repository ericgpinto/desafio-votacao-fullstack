package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record AgendaListResponse(
        @Schema(description = "Titulo da pauta", example = "Pauta 1")
        String title,
        @Schema(description = "Descrição da pauta", example = "Descrição 1")
        String description,
        @Schema(description = "Horário de abertura da sessão de votação")
        LocalDateTime voteOpeningTime,
        @Schema(description = "Horário de fechamento da sessão de votação")
        LocalDateTime voteClosingTime
) {
}
