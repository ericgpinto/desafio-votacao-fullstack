package com.ericpinto.desafiovotacaofullstack.domain.vote.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "associate")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociateEntity {

    private String id;
    private String name;
    private String legalDocumentNumber;
}
