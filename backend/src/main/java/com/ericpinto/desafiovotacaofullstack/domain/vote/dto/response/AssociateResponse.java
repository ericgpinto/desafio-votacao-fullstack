package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa o objeto de retorno ao consultar dados do associado")
public record AssociateResponse(
        @Schema(description = "ID do associado")
        String id,
        @Schema(description = "Nome do associado")
        String name,
        @Schema(description = "Documento do associado")
        String legalDocumentNumber
) {
}
