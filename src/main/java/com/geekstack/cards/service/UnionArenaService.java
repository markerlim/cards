package com.geekstack.cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.UnionArenaCard;
import com.geekstack.cards.repository.UnionArenaRepository;

import java.util.List;

@Service
public class UnionArenaService {

    @Autowired
    private UnionArenaRepository unionArenaRepository;

    public Page<UnionArenaCard> allUnionArena(Pageable pageable) {

        return unionArenaRepository.findAll(pageable);

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

    public Page<UnionArenaCard> findAllByCardUid(String cardUid,Pageable pageable) {
        // Find cards by priceFullaId only
        return unionArenaRepository.findAllByCardUid(cardUid, pageable);
    }

    public Page<UnionArenaCard> findCardByAnime(String anime, String rarity, String color, String triggerState,
            String booster, String priceYytId, Pageable pageable) {
        // If all filters are null or empty, fall back to just anime
        if ((rarity == null || rarity.isEmpty()) && (color == null || color.isEmpty())
                && (triggerState == null || triggerState.isEmpty()) && (booster == null || booster.isEmpty())
                && (priceYytId == null || priceYytId.isEmpty())) {
            return unionArenaRepository.findByAnime(anime, pageable);
        }
        // Handle the query with filters
        return unionArenaRepository.findByFilters(anime,
                rarity == null ? "" : rarity,
                color == null ? "" : color,
                triggerState == null ? "" : triggerState,
                booster == null ? "" : booster,
                priceYytId == null ? "" : priceYytId,
                pageable);
    }

}