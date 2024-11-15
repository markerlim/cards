package com.geekstack.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekstack.cards.model.UnionArenaCard;
import com.geekstack.cards.service.UnionArenaService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "http://localhost:3000" }) 
@RestController
@RequestMapping("/api/unionarena")
public class UnionArenaController {
    
    @Autowired
    private UnionArenaService unionArenaService;

        // http//localhost:8080/unionarena
    // put ?q to search by cardname, ?priceYytIdd for price_yyt_id, ?priceFullaId
    // for price_fulla_id, ?cardUid for cardUid
    @GetMapping("")
    public ResponseEntity<Page<UnionArenaCard>> getAllUnionArena(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "priceYytId", required = false) String priceYytId,
            @RequestParam(value = "priceFullaId", required = false) String priceFullaId,
            @RequestParam(value = "cardUid", required = false) String cardUid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Create a Pageable object for pagination
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("booster").ascending().and(Sort.by("cardUid").ascending()));
        Page<UnionArenaCard> pagedCards;

        // Perform filtering based on the presence of query parameters
        if (query != null && !query.isEmpty()) {
            // Perform full-text search using the query parameter
            pagedCards = unionArenaService.searchUnionArenaByText(query, pageable);
        } else {
            // If no query is provided, check for optional filters and return results
            if (priceYytId != null && !priceYytId.isEmpty()) {
                pagedCards = unionArenaService.findAllByPriceYytId(priceYytId, pageable);
            } else if (priceFullaId != null && !priceFullaId.isEmpty()) {
                pagedCards = unionArenaService.findAllByPriceFullaId(priceFullaId, pageable);
            } else if (cardUid != null && !cardUid.isEmpty()) {
                pagedCards = unionArenaService.findAllByCardUid(cardUid, pageable);
            } else {
                // No filters, return all cards with pagination
                pagedCards = unionArenaService.allUnionArena(pageable);
            }
        }

        return new ResponseEntity<>(pagedCards, HttpStatus.OK);
    }

    @GetMapping("/{anime}")
    public ResponseEntity<Page<UnionArenaCard>> getAllUAbyAnime(
            @PathVariable String anime,
            @RequestParam(value = "booster", required = false) String booster,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "rarity", required = false) String rarity,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size,
                Sort.by("booster").ascending().and(Sort.by("cardUid").ascending()));

        Page<UnionArenaCard> pagedCards;

        if (booster != null || color != null || rarity != null) {
            pagedCards = unionArenaService.findByAnimeAndFilters(anime, booster, rarity, color, pageable);
        } else {
            pagedCards = unionArenaService.findByAnime(anime, pageable);
        }

        return new ResponseEntity<>(pagedCards, HttpStatus.OK);
    }

}
