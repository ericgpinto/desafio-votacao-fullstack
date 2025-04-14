package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa o objeto de retorno quando cria uma nova Pauta")
public record AgendaRegisterResponse(
        @Schema(description = "ID único da pauta", example = "676f4aa77c1e3246139bcc6a")
        String id,
        @Schema(description = "Titulo da pauta", example = "Pauta 1")
        String title,
        @Schema(description = "Descrição da pauta", example = "Descrição 1")
        String description
) {
}
