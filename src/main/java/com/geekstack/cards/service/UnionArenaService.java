package com.geekstack.cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.UnionArenaCard;
import com.geekstack.cards.repository.UnionArenaRepository;

import java.util.List;

@Service
public class UnionArenaService {

    @Autowired
    private UnionArenaRepository unionArenaRepository;

    public List<UnionArenaCard> allUnionArena() {

        return unionArenaRepository.findAll();

    }

    public List<UnionArenaCard> searchUnionArenaByText(String searchTerm) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchTerm);
        return unionArenaRepository.findAllBy(criteria);
    }

    public List<UnionArenaCard> findAllByPriceYytId(String priceYytId) {
        // Find cards by priceYytId only
        return unionArenaRepository.findAllByPriceYytId(priceYytId);
    }

    public List<UnionArenaCard> findAllByPriceFullaId(String priceFullaId) {
        // Find cards by priceFullaId only
        return unionArenaRepository.findAllByPriceFullaId(priceFullaId);
    }

    public List<UnionArenaCard> findAllByCardUid(String cardUid) {
        // Find cards by priceFullaId only
        return unionArenaRepository.findAllByCardUid(cardUid);
    }

    public List<UnionArenaCard> findCardByAnime(String anime, String rarity, String color, String triggerState,
            String booster, String priceYytId) {
        // If all filters are null or empty, fall back to just anime
        if ((rarity == null || rarity.isEmpty()) && (color == null || color.isEmpty())
                && (triggerState == null || triggerState.isEmpty()) && (booster == null || booster.isEmpty())
                && (priceYytId == null || priceYytId.isEmpty())) {
            return unionArenaRepository.findByAnime(anime);
        }
        // Handle the query with filters
        return unionArenaRepository.findByFilters(anime,
                rarity == null ? "" : rarity,
                color == null ? "" : color,
                triggerState == null ? "" : triggerState,
                booster == null ? "" : booster,
                priceYytId == null ? "" : priceYytId);
    }

}