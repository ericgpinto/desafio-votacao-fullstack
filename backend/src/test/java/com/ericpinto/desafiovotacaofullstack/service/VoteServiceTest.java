package com.ericpinto.desafiovotacaofullstack.service;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.VoteRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.VoteResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AgendaEntity;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.VoteEntity;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.DuplicateVoteException;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.EntityNotFoundException;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.EnumConstantException;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.VoteClosedException;
import com.ericpinto.desafiovotacaofullstack.infrastructure.repository.AgendaRepository;
import com.ericpinto.desafiovotacaofullstack.infrastructure.repository.AssociateRepository;
import com.ericpinto.desafiovotacaofullstack.infrastructure.repository.VoteRepository;
import com.ericpinto.desafiovotacaofullstack.domain.vote.service.VoteService;
import com.ericpinto.desafiovotacaofullstack.stub.AgendaStub;
import com.ericpinto.desafiovotacaofullstack.stub.AssociateStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.ericpinto.desafiovotacaofullstack.stub.VoteStub.createVote;
import static com.ericpinto.desafiovotacaofullstack.stub.VoteStub.createVoteRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private AssociateRepository associateRepository;

    @InjectMocks
    private VoteService voteService;

    private static final String AGENDA_ID = "676d864a196e5d4f3d6cbaa5";
    private static final String ASSOCIATE_ID = "676c4855df73fcce481d24d0";

    @Test
    void shouldThrowExceptionWhenNoEnumConstant(){
        VoteRequest voteRequest = new VoteRequest("VOTE");
        Exception exception = assertThrows(EnumConstantException.class, () -> voteService.create(AGENDA_ID, ASSOCIATE_ID, voteRequest));

        assertEquals("No enum constant VOTE", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfAgendaNotFound() {
        when(agendaRepository.findById(AGENDA_ID)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> voteService.create(AGENDA_ID, ASSOCIATE_ID, createVoteRequest()));

        assertEquals("Agenda not found", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCreateAreClosed(){
        AgendaEntity agenda = AgendaStub.createAgendaEntity();
        agenda.setVoteClosingTime(LocalDateTime.now());

        when(agendaRepository.findById(AGENDA_ID)).thenReturn(Optional.of(agenda));

        Exception exception = assertThrows(VoteClosedException.class, () -> voteService.create(AGENDA_ID, ASSOCIATE_ID, createVoteRequest()));

        assertEquals("Agenda is no longer open for voting", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfAssociateNotFound() {
        when(agendaRepository.findById(AGENDA_ID)).thenReturn(Optional.of(AgendaStub.createAgendaEntity()));
        when(associateRepository.findById(ASSOCIATE_ID)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> voteService.create(AGENDA_ID, ASSOCIATE_ID, createVoteRequest()));

        assertEquals("Associate not found", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCreateIsDuplicate() {
        when(agendaRepository.findById(AGENDA_ID)).thenReturn(Optional.of(AgendaStub.createAgendaEntityWithVotes()));
        when(associateRepository.findById(ASSOCIATE_ID)).thenReturn(Optional.of(AssociateStub.createAssociateEntity()));

        Exception exception = assertThrows(DuplicateVoteException.class, () -> voteService.create(AGENDA_ID, ASSOCIATE_ID, createVoteRequest()));

        assertEquals("Associate has already voted on this agenda", exception.getMessage());
    }

    @Test
    void shouldCreateCreate() {
        when(agendaRepository.findById(AGENDA_ID)).thenReturn(Optional.of(AgendaStub.createAgendaEntity()));
        when(associateRepository.findById(ASSOCIATE_ID)).thenReturn(Optional.of(AssociateStub.createAssociateEntity()));
        when(voteRepository.save(any(VoteEntity.class))).thenReturn(createVote());
        when(agendaRepository.save(any(AgendaEntity.class))).thenReturn(AgendaStub.createAgendaEntity());

        VoteResponse response = voteService.create(AGENDA_ID, ASSOCIATE_ID, createVoteRequest());

        assertNotNull(response);

        verify(voteRepository, times(1)).save(any(VoteEntity.class));
        verify(agendaRepository, times(1)).save(any(AgendaEntity.class));
    }

}