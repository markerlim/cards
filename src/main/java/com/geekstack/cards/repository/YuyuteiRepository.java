package com.geekstack.cards.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.CardPriceYYT;

@Repository
public interface YuyuteiRepository extends MongoRepository<CardPriceYYT, ObjectId>{

        Optional<CardPriceYYT> findCardByPriceYytId(String priceYytId);

} 