package com.ericpinto.desafiovotacaofullstack.domain.vote.mapper;

import com.ericpinto.desafiovotacaofullstack.domain.vote.dto.response.AssociateResponse;
import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AssociateEntity;
import org.springframework.stereotype.Component;

@Component
public class AssociateMapper {

    public static AssociateResponse toResponse(AssociateEntity associateEntity) {
        return new AssociateResponse(
                associateEntity.getId(),
                associateEntity.getName(),
                associateEntity.getLegalDocumentNumber()
        );
    }
}
