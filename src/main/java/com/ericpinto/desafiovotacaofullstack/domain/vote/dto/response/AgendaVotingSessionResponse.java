package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
@Schema(description = "Modelo que representa o objeto de retorno ao abrir sessão em uma pauta")
public record AgendaVotingSessionResponse(
        @Schema(description = "ID da pauta")
        String id,
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
