package com.geekstack.cards.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.CookieRunCard;

@Repository
public interface CookieRunRepository extends MongoRepository<CookieRunCard, ObjectId> {

    Page<CookieRunCard> findAllBy(TextCriteria criteria, Pageable pageable);

    Page<CookieRunCard> findByBoostercode(String boostercode, Pageable pageable);
    
    Page<CookieRunCard> findAllByCardUid(String cardUid, Pageable pageable);

}