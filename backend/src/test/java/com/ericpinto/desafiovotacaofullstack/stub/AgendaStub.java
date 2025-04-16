package com.ericpinto.desafiovotacaofullstack.stub;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.AgendaRegisterRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.AgendaVotingSessionRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaRegisterResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVoteResultResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AgendaEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AgendaStub {

    private static final String ID = "676d864a196e5d4f3d6cbaa5";
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";

    public static AgendaEntity createPartialAgendaEntity() {
        return AgendaEntity.builder()
                .id(ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .build();
    }

    public static AgendaEntity createAgendaEntity() {
        return AgendaEntity.builder()
                .id(ID)
                .title(TITLE)
                .description(DESCRIPTION)
                .voteOpeningTime(LocalDateTime.now())
                .voteClosingTime(LocalDateTime.now().plusHours(1))
                .votes(new ArrayList<>())
                .build();
    }

    public static AgendaEntity createAgendaEntityWithVotes(){
        return AgendaEntity.builder()
                .id(ID)
                .title("Title")
                .description("Description")
                .voteOpeningTime(LocalDateTime.now())
                .voteClosingTime(LocalDateTime.now().plusHours(1))
                .votes(VoteStub.createVotes())
                .build();
    }

    public static AgendaRegisterRequest createAgendaRegisterRequest() {
        return new AgendaRegisterRequest(TITLE, DESCRIPTION);
    }

    public static AgendaVotingSessionRequest createAgendaVotingSessionRequest() {
        return new AgendaVotingSessionRequest(LocalDateTime.of(2025, 4, 15, 14, 30));
    }

}
