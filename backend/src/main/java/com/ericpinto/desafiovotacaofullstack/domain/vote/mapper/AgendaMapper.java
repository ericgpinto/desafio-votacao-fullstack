package com.ericpinto.desafiovotacaofullstack.domain.vote.mapper;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.AgendaRegisterRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaRegisterResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVotingSessionResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AgendaEntity;
import org.springframework.stereotype.Component;

@Component
public class AgendaMapper {

    public static AgendaEntity toEntity(AgendaRegisterRequest request) {
        return AgendaEntity.builder()
                .title(request.title())
                .description(request.description())
                .build();
    }

    public static AgendaResponse toResponse(AgendaEntity entity) {
        return new AgendaResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getVoteOpeningTime(),
                entity.getVoteClosingTime()
        );
    }

    public static AgendaRegisterResponse toRegisterResponse(AgendaEntity entity) {
        return new AgendaRegisterResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription()
        );
    }

    public static AgendaVotingSessionResponse toVotingSessionResponse(AgendaEntity entity) {
        return new AgendaVotingSessionResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getVoteOpeningTime(),
                entity.getVoteClosingTime()
        );
    }
}
