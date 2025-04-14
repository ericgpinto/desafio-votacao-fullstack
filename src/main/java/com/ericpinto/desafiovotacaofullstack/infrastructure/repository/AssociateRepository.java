package com.ericpinto.desafiovotacaofullstack.infrastructure.repository;

import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AssociateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociateRepository extends MongoRepository<AssociateEntity, String> {
}
