package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa o objeto de retorno ao contabilizar votos de uma pauta")
public record AgendaVoteResultResponse(
        @Schema(description = "ID da pauta")
        String id,
        @Schema(description = "Titulo da pauta", example = "Pauta 1")
        String title,
        @Schema(description = "Descrição da pauta", example = "Descrição 1")
        String description,
        @Schema(description = "Total de votos contabilizados", example = "50")
        Long totalVotes,
        @Schema(description = "Total de votos para SIM contabilizados", example = "25")
        Long countingYesVotes,
        @Schema(description = "Total de votos para NÃO contabilizados", example = "25")
        Long countingNoVotes
) {
}
