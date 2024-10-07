package com.geekstack.cards.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.CardPriceFULLA;

@Repository
public interface FullaheadRepository extends MongoRepository<CardPriceFULLA, ObjectId>{
    
    Optional<CardPriceFULLA> findCardByPriceFullaId(String priceFullaId);

}
