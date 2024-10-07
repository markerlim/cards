package com.geekstack.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping()
    public String welcome(){
        return "Hi this is Geekstack API";
    }

    @GetMapping("/unionarena")
    public ResponseEntity<List<UnionArenaCard>> getAllUnionArena(){
        return new ResponseEntity<List<UnionArenaCard>>(unionArenaService.allUnionArena(),HttpStatus.OK);
    }
    
    @GetMapping("/unionarena/{cardUid}")
    public ResponseEntity<Optional<UnionArenaCard>> getOneUnionArena(@PathVariable String cardUid){
        return new ResponseEntity<Optional<UnionArenaCard>>(unionArenaService.findCardByCardUid(cardUid),HttpStatus.OK);
    }

    @GetMapping("/yyt")
    public ResponseEntity<List<CardPriceYYT>> getAllYuyutei(){
        return new ResponseEntity<List<CardPriceYYT>>(yuyuteiService.allCards(),HttpStatus.OK);
    }

    @GetMapping("/yyt/{priceYytId}")
    public ResponseEntity<Optional<CardPriceYYT>> getOneYYTCard(@PathVariable String priceYytId){
        return new ResponseEntity<Optional<CardPriceYYT>>(yuyuteiService.findCardByPriceYytId(priceYytId),HttpStatus.OK);
    }

    @GetMapping("/fulla")
    public ResponseEntity<List<CardPriceFULLA>> getAllFulla(){
        return new ResponseEntity<List<CardPriceFULLA>>(fullaheadService.allFHCards(),HttpStatus.OK);
    }

    @GetMapping("/fulla/{priceFullaId}")
    public ResponseEntity<Optional<CardPriceFULLA>> getOneFullaCard(@PathVariable String priceFullaId){
        return new ResponseEntity<Optional<CardPriceFULLA>>(fullaheadService.findCardByPriceFullaId(priceFullaId),HttpStatus.OK);
    }

}