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

import com.geekstack.cards.model.DragonBallzFWCard;
import com.geekstack.cards.service.DragonBallzFWService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "http://localhost:3000" })
@RestController
@RequestMapping("/api/dragonballzfw")
public class DragonballzfwController {

    @Autowired
    private DragonBallzFWService dragonBallzFWService;

     @GetMapping("")
    public ResponseEntity<Page<DragonBallzFWCard>> getAllDragonballzfw(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "cardUid", required = false) String cardUid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Create a Pageable object for pagination
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("booster").ascending().and(Sort.by("cardUid").ascending()));
        Page<DragonBallzFWCard> pagedCards;

        // Perform filtering based on the presence of query parameters
        if (query != null && !query.isEmpty()) {
            // Perform full-text search using the query parameter
            pagedCards = dragonBallzFWService.searchDragonballzfwByText(query, pageable);
        } else {
            if (cardUid != null && !cardUid.isEmpty()) {
                pagedCards = dragonBallzFWService.findAllByCardUid(cardUid, pageable);
            } else {
                // No filters, return all cards with pagination
                pagedCards = dragonBallzFWService.allDragonballzfw(pageable);
            }
        }

        return new ResponseEntity<>(pagedCards, HttpStatus.OK);
    }

    @GetMapping("/{booster}")
    public ResponseEntity<Page<DragonBallzFWCard>> getAllDragonballzfwByBooster(
        @PathVariable String booster,
        @RequestParam(value = "cardtype", required = false) String cardtype,
        @RequestParam(value = "color", required = false) String colorLower,
        @RequestParam(value = "rarity", required = false) String rarity,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size){
        
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("booster").ascending().and(Sort.by("cardUid").ascending()));

        Page<DragonBallzFWCard> pagedCards;

        if (cardtype != null || colorLower != null || rarity != null) {
            pagedCards = dragonBallzFWService.findByBoosterAndFilter(booster, cardtype, rarity, colorLower, pageable);
        } else {
            pagedCards = dragonBallzFWService.findByBooster(booster, pageable);
        }

        return new ResponseEntity<>(pagedCards, HttpStatus.OK);   

    }
}
