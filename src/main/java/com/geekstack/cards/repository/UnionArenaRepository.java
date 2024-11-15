package com.geekstack.cards.repository;

import java.util.List;

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

    Page<UnionArenaCard> findAllBy(TextCriteria criteria, Pageable pageable);

    @Query("{ 'anime': ?0, " +
            "'booster': { $eq: ?1 }, " +
            "'rarity': { $eq: ?2 }, " +
            "'color': { $eq: ?3 } }")
    List<UnionArenaCard> findByAnimeAndBoosterAndRarityAndColor(
            String anime,
            String booster,
            String rarity,
            String color);

    Page<UnionArenaCard> findAllByPriceYytId(String priceYytId, Pageable pageable);

    Page<UnionArenaCard> findAllByPriceFullaId(String priceFullaId, Pageable pageable);

    Page<UnionArenaCard> findAllByCardUid(String cardUid, Pageable pageable);

    Page<UnionArenaCard> findByAnime(String anime, Pageable pageable);
}