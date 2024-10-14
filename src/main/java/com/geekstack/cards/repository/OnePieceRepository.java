package com.geekstack.cards.repository;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.OnePieceCard;

@Repository
public interface OnePieceRepository extends MongoRepository<OnePieceCard, ObjectId> {

        Page<OnePieceCard> findAllBy(TextCriteria criteria, Pageable pageable);

}
