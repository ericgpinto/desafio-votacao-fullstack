package com.ericpinto.desafiovotacaofullstack.infrastructure.api.controller;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.AgendaRegisterRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaRegisterResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVoteResultResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVotingSessionResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.service.AgendaService;
import com.ericpinto.desafiovotacaofullstack.infrastructure.api.exception.CustomError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/agendas")
@Tag(name = "API de Pautas")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @Operation(summary = "Cadastrar uma nova pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Pauta cadastrada",
                    content = @Content(schema = @Schema(implementation = AgendaRegisterResponse.class))),
    })
    @PostMapping("/register")
    public ResponseEntity<AgendaRegisterResponse> register(@Valid @RequestBody AgendaRegisterRequest agendaRegisterRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(agendaService.create(agendaRegisterRequest));
    }

    @Operation(summary = "Abrir sessão de votação em uma pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão de votação aberta", content = @Content(schema = @Schema(implementation = AgendaVotingSessionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada", content = @Content(schema = @Schema(implementation = CustomError.class)))
    })
    @PutMapping("/{id}/open-voting")
    public ResponseEntity<AgendaVotingSessionResponse> openVoting(@PathVariable String id) {
        AgendaVotingSessionResponse response = agendaService.openSessionToVote(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Contabilizar votos de uma pauta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Votos contabilizados", content = @Content(schema = @Schema(implementation = AgendaVoteResultResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pauta não encontrada")
    })
    @GetMapping("/{id}/counting-votes")
    public ResponseEntity<AgendaVoteResultResponse> getCountingVotes(@PathVariable String id) {
        return ResponseEntity.ok(agendaService.countingVotes(id));
    }

}
