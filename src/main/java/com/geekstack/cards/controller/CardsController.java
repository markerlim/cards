package com.geekstack.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekstack.cards.model.CardPriceFULLA;
import com.geekstack.cards.model.CardPriceYYT;
import com.geekstack.cards.model.UnionArenaCard;
import com.geekstack.cards.service.FullaheadService;
import com.geekstack.cards.service.UnionArenaService;
import com.geekstack.cards.service.YuyuteiService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CardsController {

    @Autowired
    private UnionArenaService unionArenaService;
    @Autowired
    private YuyuteiService yuyuteiService;
    @Autowired
    private FullaheadService fullaheadService;

    // http//localhost:8080/
    @GetMapping()
    public String welcome() {
        return "Hi this is Geekstack API\n The api is still a work in progress with minimal functions";
    }


    // http//localhost:8080/unionarena
    // put ?q to search by cardname, ?priceYytIdd for price_yyt_id, ?priceFullaId for price_fulla_id, ?cardUid for cardUid
    @GetMapping("/unionarena")
    public ResponseEntity<List<UnionArenaCard>> getAllUnionArena(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "priceYytId", required = false) String priceYytId,
            @RequestParam(value = "priceFullaId", required = false) String priceFullaId,
            @RequestParam(value = "cardUid", required = false) String cardUid) {

        List<UnionArenaCard> allcards;

        if (query != null && !query.isEmpty()) {
            // Perform full-text search using the query parameter
            allcards = unionArenaService.searchUnionArenaByText(query);
        } else {
            // If no query is provided, return all cards, optionally filtered by priceYytId
            if (priceYytId != null && !priceYytId.isEmpty()) {
                allcards = unionArenaService.findAllByPriceYytId(priceYytId);
            } else if (priceFullaId != null && !priceFullaId.isEmpty()){
                allcards = unionArenaService.findAllByPriceFullaId(priceFullaId);
            } else if (cardUid != null && !cardUid.isEmpty()){
                allcards = unionArenaService.findAllByCardUid(cardUid);
            } else {
                allcards = unionArenaService.allUnionArena();
            }
        }

        return new ResponseEntity<>(allcards, HttpStatus.OK);
    }

    // http//localhost:8080/unionarena/anime?rarity={rarity}&color={color}&triggerState={triggerState}&booster={booster}
    // can remove fields that are not needed
    @GetMapping("/unionarena/{anime}")
    public ResponseEntity<List<UnionArenaCard>> getUnionArenaByAnime(@PathVariable String anime,
            @RequestParam(value = "rarity", required = false) String rarity,
            @RequestParam(value = "color", required = false) String color,
            @RequestParam(value = "triggerState", required = false) String triggerState,
            @RequestParam(value = "booster", required = false) String booster,
            @RequestParam(value = "priceYytId", required = false) String priceYytId) {
        List<UnionArenaCard> cards = unionArenaService.findCardByAnime(anime, rarity, color, triggerState, booster,
                priceYytId);
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    // http//localhost:8080/yyt
    @GetMapping("/yyt")
    public ResponseEntity<List<CardPriceYYT>> getAllYuyutei() {
        return new ResponseEntity<List<CardPriceYYT>>(yuyuteiService.allCards(), HttpStatus.OK);
    }

    // http//localhost:8080/yyt/{priceYytId}
    @GetMapping("/yyt/{priceYytId}")
    public ResponseEntity<Optional<CardPriceYYT>> getOneYYTCard(@PathVariable String priceYytId) {
        return new ResponseEntity<Optional<CardPriceYYT>>(yuyuteiService.findCardByPriceYytId(priceYytId),
                HttpStatus.OK);
    }

    // http//localhost:8080/fulla
    @GetMapping("/fulla")
    public ResponseEntity<List<CardPriceFULLA>> getAllFulla() {
        return new ResponseEntity<List<CardPriceFULLA>>(fullaheadService.allFHCards(), HttpStatus.OK);
    }

    // http//localhost:8080/fulla/{priceFullaId}
    @GetMapping("/fulla/{priceFullaId}")
    public ResponseEntity<Optional<CardPriceFULLA>> getOneFullaCard(@PathVariable String priceFullaId) {
        return new ResponseEntity<Optional<CardPriceFULLA>>(fullaheadService.findCardByPriceFullaId(priceFullaId),
                HttpStatus.OK);
    }

}