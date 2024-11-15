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

import com.geekstack.cards.model.DragonBallzFWCard;
import com.geekstack.cards.repository.DragonBallzFWRepository;

@Service
public class DragonBallzFWService {

    @Autowired
    private DragonBallzFWRepository dragonBallzFWRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Page<DragonBallzFWCard> allDragonballzfw(Pageable pageable) {

        return dragonBallzFWRepository.findAll(pageable);

    }

    public List<DragonBallzFWCard> allDragonballzFWPage() {
        // Define sorting by booster and cardUid
        Sort sort = Sort.by("booster").ascending().and(Sort.by("cardUid").ascending());
        return dragonBallzFWRepository.findAll(sort); // Fetch all sorted records from the repository
    }

    public Page<DragonBallzFWCard> searchDragonballzfwByText(String searchTerm, Pageable pageable) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchTerm);
        return dragonBallzFWRepository.findAllBy(criteria, pageable);
    }

    public Page<DragonBallzFWCard> findAllByCardUid(String cardUid, Pageable pageable) {
        // Find cards by priceFullaId only
        return dragonBallzFWRepository.findAllByCardUid(cardUid, pageable);
    }

    public Page<DragonBallzFWCard> findByBooster(String booster, Pageable pageable) {
        return dragonBallzFWRepository.findByBooster(booster, pageable);
    }

    public Page<DragonBallzFWCard> findByBoosterAndFilter(
            String booster,
            String cardtype,
            String rarity,
            String colorLower,
            Pageable pageable) {

        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("booster").is(booster));

        if (cardtype != null) {
            query.addCriteria(Criteria.where("cardtype").is(cardtype));
        }
        if (rarity != null) {
            query.addCriteria(Criteria.where("rarity").is(rarity));
        }
        if (colorLower != null) {
            query.addCriteria(Criteria.where("colorLower").is(colorLower));
        }

        // Execute the query
        List<DragonBallzFWCard> filteredCards = mongoTemplate.find(query, DragonBallzFWCard.class);
        long count = mongoTemplate.count(query.skip(-1).limit(-1), DragonBallzFWCard.class);

        return new PageImpl<>(filteredCards, pageable, count);
    }



}
