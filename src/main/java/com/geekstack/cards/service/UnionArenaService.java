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

import com.geekstack.cards.model.UnionArenaCard;
import com.geekstack.cards.repository.UnionArenaRepository;

@Service
public class UnionArenaService {

    @Autowired
    private UnionArenaRepository unionArenaRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    public Page<UnionArenaCard> allUnionArena(Pageable pageable) {

        return unionArenaRepository.findAll(pageable);

    }

    public List<UnionArenaCard> allUnionArenaNoPage() {
        // Define sorting by booster and cardUid
        Sort sort = Sort.by("booster").ascending().and(Sort.by("cardUid").ascending());
        return unionArenaRepository.findAll(sort); // Fetch all sorted records from the repository
    }

    public Page<UnionArenaCard> searchUnionArenaByText(String searchTerm, Pageable pageable) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchTerm);
        return unionArenaRepository.findAllBy(criteria, pageable);
    }

    public Page<UnionArenaCard> findAllByPriceYytId(String priceYytId, Pageable pageable) {
        // Find cards by priceYytId only
        return unionArenaRepository.findAllByPriceYytId(priceYytId, pageable);
    }

    public Page<UnionArenaCard> findAllByPriceFullaId(String priceFullaId, Pageable pageable) {
        // Find cards by priceFullaId only
        return unionArenaRepository.findAllByPriceFullaId(priceFullaId, pageable);
    }

    public Page<UnionArenaCard> findAllByCardUid(String cardUid, Pageable pageable) {
        // Find cards by priceFullaId only
        return unionArenaRepository.findAllByCardUid(cardUid, pageable);
    }

    public Page<UnionArenaCard> findByAnime(String anime, Pageable pageable) {
        // Find cards by Anime only
        return unionArenaRepository.findByAnime(anime, pageable);
    }

    public Page<UnionArenaCard> findByAnimeAndFilters(
            String anime,
            String booster,
            String rarity,
            String color,
            Pageable pageable) {

        Query query = new Query().with(pageable);
        query.addCriteria(Criteria.where("anime").is(anime));

        if (booster != null) {
            query.addCriteria(Criteria.where("booster").is(booster));
        }
        if (rarity != null) {
            query.addCriteria(Criteria.where("rarity").is(rarity));
        }
        if (color != null) {
            query.addCriteria(Criteria.where("color").is(color));
        }

        // Execute the query
        List<UnionArenaCard> filteredCards = mongoTemplate.find(query, UnionArenaCard.class);
        long count = mongoTemplate.count(query.skip(-1).limit(-1), UnionArenaCard.class);

        return new PageImpl<>(filteredCards, pageable, count);
    }

}