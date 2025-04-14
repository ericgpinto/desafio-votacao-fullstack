package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Modelo que representa o objeto de requisição enviado para votar em uma pauta")
public record VoteRequest(
        @Schema(description = "Voto do associado", example = "YES")
        @NotNull(message = "O voto não pode ser nulo")
        @NotBlank(message = "O voto não pode estar vazio")
        String vote
) {
}
