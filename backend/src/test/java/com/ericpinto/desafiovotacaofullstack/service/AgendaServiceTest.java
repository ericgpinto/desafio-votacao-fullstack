package com.ericpinto.desafiovotacaofullstack.service;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.AgendaVotingSessionRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaRegisterResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVoteResultResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AgendaVotingSessionResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AgendaEntity;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.EntityNotFoundException;
import com.ericpinto.desafiovotacaofullstack.infrastructure.repository.AgendaRepository;
import com.ericpinto.desafiovotacaofullstack.domain.vote.service.AgendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.ericpinto.desafiovotacaofullstack.stub.AgendaStub.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @Mock
    private AgendaRepository agendaRepository;

    @InjectMocks
    private AgendaService agendaService;

    private static final String ID = "676d864a196e5d4f3d6cbaa5";

    @Test
    void shouldCreateAgenda() {
        when(agendaRepository.save(any(AgendaEntity.class))).thenReturn(createPartialAgendaEntity());

        AgendaRegisterResponse response = agendaService.create(createAgendaRegisterRequest());

        assertNotNull(response);
        verify(agendaRepository, times(1)).save(any(AgendaEntity.class));
    }

    @Test
    void shouldGetAll(){

        when(agendaRepository.findAll()).thenReturn(List.of(createAgendaEntity()));

        List<AgendaResponse> response = agendaService.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
    }


    @Test
    void shouldGetAgendaById() {
        when(agendaRepository.findById(ID)).thenReturn(Optional.of(createAgendaEntity()));
        AgendaResponse response = agendaService.findById(ID);
        assertNotNull(response);
    }

    @Test
    void shouldThrowExceptionIfAgendaNotFound() {
        when(agendaRepository.findById(ID)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> agendaService.openSessionToVote(ID, createAgendaVotingSessionRequest()));

        assertEquals("Agenda not found", exception.getMessage());
    }

    @Test
    void shouldOpenSessionToVoteWithDefaultEndTime(){
        AgendaVotingSessionRequest agendaVotingSessionRequest = new AgendaVotingSessionRequest(null);
        when(agendaRepository.findById(ID)).thenReturn(Optional.of(createPartialAgendaEntity()));
        when(agendaRepository.save(any(AgendaEntity.class))).thenReturn(createAgendaEntity());


        AgendaVotingSessionResponse response = agendaService.openSessionToVote(ID, agendaVotingSessionRequest);

        assertNotNull(response);
        verify(agendaRepository, times(1)).save(any(AgendaEntity.class));

    }

    @Test
    void shouldOpenSessionToVote(){
        when(agendaRepository.findById(ID)).thenReturn(Optional.of(createPartialAgendaEntity()));
        when(agendaRepository.save(any(AgendaEntity.class))).thenReturn(createAgendaEntity());

        AgendaVotingSessionResponse response = agendaService.openSessionToVote(ID, createAgendaVotingSessionRequest());

        assertNotNull(response);
        verify(agendaRepository, times(1)).save(any(AgendaEntity.class));

    }

    @Test
    void shouldCountingVotes(){
        when(agendaRepository.findById(ID)).thenReturn(Optional.of(createAgendaEntityWithVotes()));

        AgendaVoteResultResponse response = agendaService.countingVotes(ID);

        assertNotNull(response);

        assertEquals(3, response.totalVotes());
        assertEquals(2, response.countingYesVotes());
        assertEquals(1, response.countingNoVotes());
    }
}