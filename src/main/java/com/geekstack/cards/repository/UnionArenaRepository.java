package com.geekstack.cards.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.UnionArenaCard;

import java.util.List;

@Repository
public interface UnionArenaRepository extends MongoRepository<UnionArenaCard, ObjectId> {

    @Query("{ 'anime': ?0, 'rarity': { $regex: ?1 }, 'color': { $regex: ?2 }, 'triggerState': { $regex: ?3 }, 'booster' : { $regex: ?4}, 'priceYytId' : ?5 }")
    List<UnionArenaCard> findByFilters(String anime, String rarity, String color, String triggerState, String booster, String priceYytId);

    List<UnionArenaCard> findAllBy(TextCriteria criteria);

    List<UnionArenaCard> findAllByPriceYytId(String priceYytId);

    List<UnionArenaCard> findAllByPriceFullaId(String priceFullaId);

    List<UnionArenaCard> findAllByCardUid(String cardUid);
    
    List<UnionArenaCard> findByAnime(String anime);
}