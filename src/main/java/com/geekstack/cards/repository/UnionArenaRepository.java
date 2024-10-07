package com.geekstack.cards.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.UnionArenaCard;
import java.util.Optional;

@Repository
public interface UnionArenaRepository extends MongoRepository<UnionArenaCard, ObjectId>{

    Optional<UnionArenaCard> findCardByCardUid(String cardUid);

}