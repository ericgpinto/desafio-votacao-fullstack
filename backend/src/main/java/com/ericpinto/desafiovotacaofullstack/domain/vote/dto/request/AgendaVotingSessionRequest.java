package com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request;

import java.time.LocalDateTime;

public record AgendaVotingSessionRequest(
        LocalDateTime voteClosingTime
) {
}
