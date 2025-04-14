package com.ericpinto.desafiovotacaofullstack.stub;

import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AssociateEntity;

public class AssociateStub {

    public static AssociateEntity createAssociateEntity() {
        return AssociateEntity.builder()
                .id("676c4855df73fcce481d24d0")
                .name("Associate 1")
                .legalDocumentNumber("35868004019")
                .build();
    }
}
