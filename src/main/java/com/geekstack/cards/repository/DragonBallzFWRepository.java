package com.geekstack.cards.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.DragonBallzFWCard;

@Repository
public interface DragonBallzFWRepository extends MongoRepository<DragonBallzFWCard, ObjectId>  {

    Page<DragonBallzFWCard> findAllBy(TextCriteria criteria, Pageable pageable);
}

