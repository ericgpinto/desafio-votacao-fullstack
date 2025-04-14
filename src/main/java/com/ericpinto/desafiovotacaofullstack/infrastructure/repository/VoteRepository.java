package com.ericpinto.desafiovotacaofullstack.infrastructure.repository;

import com.ericpinto.desafiovotacaofullstack.domain.vote.entity.VoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends MongoRepository<VoteEntity, String> {
}
