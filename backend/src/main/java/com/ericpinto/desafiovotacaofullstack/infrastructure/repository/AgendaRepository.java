package com.ericpinto.desafiovotacaofullstack.infrastructure.repository;

import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.AgendaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendaRepository extends MongoRepository<AgendaEntity, String> {
}
