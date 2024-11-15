package com.geekstack.cards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.OnePieceCard;
import com.geekstack.cards.repository.OnePieceRepository;

@Service
public class OnePieceService {

    @Autowired
    private OnePieceRepository onePieceRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Page<OnePieceCard> allOnePiece(Pageable pageable) {

        return onePieceRepository.findAll(pageable);

    }

    public List<OnePieceCard> allOnePiecePage() {
        // Define sorting by booster and cardUid
        Sort sort = Sort.by("booster").ascending().and(Sort.by("cardUid").ascending());
        return onePieceRepository.findAll(sort); // Fetch all sorted records from the repository
    }

    public Page<OnePieceCard> searchOnepieceByText(String searchTerm, Pageable pageable) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchTerm);
        return onePieceRepository.findAllBy(criteria, pageable);
    }

    public Page<OnePieceCard> findAllByCardUid(String cardUid, Pageable pageable) {
        // Find cards by priceFullaId only
        return onePieceRepository.findAllByCardUid(cardUid, pageable);
    }

    public Page<OnePieceCard> findByBooster(String booster, Pageable pageable) {
        return onePieceRepository.findByBooster(booster, pageable);
    }

    public Page<OnePieceCard> findByBoosterAndFilter(
            String booster,
            String category,
            String rarity,
            String color,
            Pageable pageable) {

        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("booster").is(booster));

        if (category != null) {
            query.addCriteria(Criteria.where("category").is(category));
        }
        if (rarity != null) {
            query.addCriteria(Criteria.where("rarity").is(rarity));
        }
        if (color != null) {
            query.addCriteria(Criteria.where("color").is(color));
        }

        // Execute the query
        List<OnePieceCard> filteredCards = mongoTemplate.find(query, OnePieceCard.class);
        long count = mongoTemplate.count(query.skip(-1).limit(-1), OnePieceCard.class);

        return new PageImpl<>(filteredCards, pageable, count);
    }

}
