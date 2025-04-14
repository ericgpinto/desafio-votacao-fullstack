package com.ericpinto.desafiovotacaofullstack.domain.vote.entity;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.VoteRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.exception.DuplicateVoteException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "vote")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class VoteEntity {

    @Id
    private String id;
    private VoteEnum vote;
    private AssociateEntity associate;
    private LocalDateTime createdAt;

    private static final Logger log = LoggerFactory.getLogger(VoteEntity.class);

    public static VoteEntity create(VoteRequest request, AgendaEntity agenda, AssociateEntity associate){
        validate(agenda, associate);

        return  VoteEntity.builder()
                .vote(VoteEnum.valueOf(request.vote()))
                .associate(associate)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static void validate(AgendaEntity agenda, AssociateEntity associate){
        boolean alreadyVoted = agenda.getVotes().stream()
                .anyMatch(vote -> vote.getAssociate().equals(associate));

        if (alreadyVoted) {
            log.error("Associated has already voted");
            throw new DuplicateVoteException("Associate has already voted on this agenda");
        }
    }

}
