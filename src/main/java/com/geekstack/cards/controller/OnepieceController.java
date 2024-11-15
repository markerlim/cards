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

import com.geekstack.cards.model.OnePieceCard;
import com.geekstack.cards.service.OnePieceService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "http://localhost:3000" })
@RestController
@RequestMapping("/api/onepiece")
public class OnepieceController {

    @Autowired
    private OnePieceService onePieceService;

    @GetMapping("")
    public ResponseEntity<Page<OnePieceCard>> getAllOnePiece(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "cardUid", required = false) String cardUid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Create a Pageable object for pagination
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("booster").ascending().and(Sort.by("cardUid").ascending()));
        Page<OnePieceCard> pagedCards;

        // Perform filtering based on the presence of query parameters
        if (query != null && !query.isEmpty()) {
            // Perform full-text search using the query parameter
            pagedCards = onePieceService.searchOnepieceByText(query, pageable);
        } else {
            if (cardUid != null && !cardUid.isEmpty()) {
                pagedCards = onePieceService.findAllByCardUid(cardUid, pageable);
            } else {
                // No filters, return all cards with pagination
                pagedCards = onePieceService.allOnePiece(pageable);
            }
        }

        return new ResponseEntity<>(pagedCards, HttpStatus.OK);
    }

    @GetMapping("/{booster}")
    public ResponseEntity<Page<OnePieceCard>> getAllOPByBooster(
        @PathVariable String booster,
        @RequestParam(value = "category", required = false) String category,
        @RequestParam(value = "color", required = false) String color,
        @RequestParam(value = "rarity", required = false) String rarity,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size){
        
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("booster").ascending().and(Sort.by("cardUid").ascending()));

        Page<OnePieceCard> pagedCards;

        if (category != null || color != null || rarity != null) {
            pagedCards = onePieceService.findByBoosterAndFilter(booster, category, rarity, color, pageable);
        } else {
            pagedCards = onePieceService.findByBooster(booster, pageable);
        }

        return new ResponseEntity<>(pagedCards, HttpStatus.OK);   

    }
}
