package com.ericpinto.desafiovotacaofullstack.domain.vote.mapper;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.request.VoteRequest;
import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.VoteResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.VoteEntity;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.VoteEnum;
import org.springframework.stereotype.Component;

@Component
public class VoteMapper {

    public static VoteEntity toEntity(VoteRequest request){
        return VoteEntity.builder()
                .vote(VoteEnum.valueOf(request.vote()))
                .build();
    }

    public static VoteResponse toResponse(VoteEntity entity){
        return new VoteResponse(
                entity.getVote().toString(),
                entity.getCreatedAt(),
                AssociateMapper.toResponse(entity.getAssociate())
        );
    }
}
