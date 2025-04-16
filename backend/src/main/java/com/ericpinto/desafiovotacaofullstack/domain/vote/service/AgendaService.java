package com.ericpinto.desafiovotacaofullstack.domain.vote.service;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.AgendaRegisterRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.AgendaVotingSessionRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaRegisterResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVoteResultResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVotingSessionResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AgendaEntity;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.VoteEnum;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.EntityNotFoundException;
import com.ericpinto.desafiovotacaofullstack.domain.vote.mapper.AgendaMapper;
import com.ericpinto.desafiovotacaofullstack.infrastructure.repository.AgendaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class AgendaService {

    private static final Logger log = LoggerFactory.getLogger(AgendaService.class);
    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public AgendaRegisterResponse create(AgendaRegisterRequest agendaRegisterRequest) {
        log.info("Creating new agenda.");
        AgendaEntity agendaEntity = AgendaEntity.create(agendaRegisterRequest);

        agendaRepository.save(agendaEntity);

        log.info("Created new agenda.");
        return AgendaMapper.toRegisterResponse(agendaEntity);
    }

    public List<AgendaResponse> findAll(){
        log.info("Finding all agendas.");
        return agendaRepository.findAll()
                .stream()
                .map(AgendaMapper::toResponse)
                .toList();
    }

    public AgendaResponse findById(String id) {
        log.info("Finding agenda by id.");
        return AgendaMapper.toResponse(getById(id)) ;
    }

    public AgendaVotingSessionResponse openSessionToVote(String id, AgendaVotingSessionRequest request) {
        log.info("Opening session to vote for agenda.");
        AgendaEntity agenda = getById(id);

        agenda.setVoteOpeningTime(LocalDateTime.now());

        if (Objects.isNull(request.voteClosingTime())){
            agenda.setVoteClosingTime(LocalDateTime.now().plusHours(1));
        } else {
            agenda.setVoteClosingTime(request.voteClosingTime());
        }


        agendaRepository.save(agenda);

        log.info("Opened session to vote for agenda.");
        return AgendaMapper.toVotingSessionResponse(agenda);

    }

    private AgendaEntity getById(String id) {
        return agendaRepository.findById(id).orElseThrow(() ->
        {
            log.error("Agenda with id {} not found.", id);
            return new EntityNotFoundException("Agenda not found");
        });
    }

    public AgendaVoteResultResponse countingVotes(String id) {
        log.info("Counting votes for agenda.");

        AgendaEntity agenda = getById(id);
        log.info("Getting result for agenda {}.", agenda.getId());

        Long countingYesVotes = agenda.getVotes().stream().filter((vote) -> vote.getVote() ==  VoteEnum.YES).count();
        Long countingNoVotes = agenda.getVotes().stream().filter((vote) -> vote.getVote() == VoteEnum.NO).count();

        return new AgendaVoteResultResponse(
                agenda.getId(),
                agenda.getTitle(),
                agenda.getDescription(),
                countingYesVotes + countingNoVotes,
                countingYesVotes,
                countingNoVotes
        );
    }
}
