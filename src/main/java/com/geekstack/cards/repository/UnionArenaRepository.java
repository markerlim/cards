package com.geekstack.cards.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.UnionArenaCard;

@Repository
public interface UnionArenaRepository extends MongoRepository<UnionArenaCard, ObjectId> {

    @Query("{ 'anime': ?0, 'rarity': { $regex: ?1 }, 'color': { $regex: ?2 }, 'triggerState': { $regex: ?3 }, 'booster' : { $regex: ?4}, 'priceYytId' : ?5 }")
    Page<UnionArenaCard> findByFilters(String anime, String rarity, String color, String triggerState, String booster, String priceYytId, Pageable pageable);

    Page<UnionArenaCard> findAllBy(TextCriteria criteria, Pageable pageable);

    Page<UnionArenaCard> findAllByPriceYytId(String priceYytId, Pageable pageable);

    Page<UnionArenaCard> findAllByPriceFullaId(String priceFullaId, Pageable pageable);

    Page<UnionArenaCard> findAllByCardUid(String cardUid, Pageable pageable);
    
    Page<UnionArenaCard> findByAnime(String anime, Pageable pageable);
}