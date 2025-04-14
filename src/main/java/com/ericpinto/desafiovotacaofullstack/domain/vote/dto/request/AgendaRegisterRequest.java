package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Modelo que representa o objeto de requisição quando cria uma nova Pauta")
public record AgendaRegisterRequest(
        @Schema(description = "Titulo da pauta", example = "Pauta 1")
        @NotNull(message = "O título não pode ser nulo")
        @NotBlank(message = "O título não pode estar vazio")
        String title,
        @Schema(description = "Descrição da pauta", example = "Descrição 1")
        String description
) {
}
