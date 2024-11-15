package com.geekstack.cards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geekstack.cards.model.CardPriceFULLA;
import com.geekstack.cards.model.CardPriceYYT;
import com.geekstack.cards.model.DragonBallzFWCard;
import com.geekstack.cards.model.HololiveCard;
import com.geekstack.cards.model.OnePieceCard;
import com.geekstack.cards.model.UnionArenaBooster;
import com.geekstack.cards.model.UnionArenaCard;
import com.geekstack.cards.service.DragonBallzFWService;
import com.geekstack.cards.service.FullaheadService;
import com.geekstack.cards.service.HololiveService;
import com.geekstack.cards.service.OnePieceService;
import com.geekstack.cards.service.UABoosterService;
import com.geekstack.cards.service.UnionArenaService;
import com.geekstack.cards.service.YuyuteiService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "http://localhost:3000" }) 
public class CardsController {

    @Autowired
    private UnionArenaService unionArenaService;
    @Autowired
    private YuyuteiService yuyuteiService;
    @Autowired
    private FullaheadService fullaheadService;
    @Autowired
    private UABoosterService uaBoosterService;
    @Autowired
    private OnePieceService onePieceService;
    @Autowired
    private DragonBallzFWService dragonBallzFWService;
    @Autowired
    private HololiveService hololiveService;

    // http//localhost:8080/
    @GetMapping(path = {"/v1"})
    public String welcome() {
        return "index"; 
    }
    
    // http//localhost:8080/data/unionarena
    @ResponseBody
    @GetMapping("/data/unionarena")
    public ResponseEntity<List<UnionArenaCard>> getAllUnionArenaNoPage() {
        return new ResponseEntity<List<UnionArenaCard>>(unionArenaService.allUnionArenaNoPage(), HttpStatus.OK);
    }

    // http//localhost:8080/data/onepiece
    @ResponseBody
    @GetMapping("/data/onepiece")
    public ResponseEntity<List<OnePieceCard>> allOnePiecePage() {
        return new ResponseEntity<List<OnePieceCard>>(onePieceService.allOnePiecePage(), HttpStatus.OK);
    }

    // http//localhost:8080/data/dragonballzfw
    @ResponseBody
    @GetMapping("/data/dragonballzfw")
    public ResponseEntity<List<DragonBallzFWCard>> allDragonBallzFWPage() {
        return new ResponseEntity<List<DragonBallzFWCard>>(dragonBallzFWService.allDragonballzFWPage(), HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/data/hololive")
    public ResponseEntity<List<HololiveCard>> allHololivePage(){
        return new ResponseEntity<List<HololiveCard>>(hololiveService.allHololivePage(),HttpStatus.OK);
    }

    // http//localhost:8080/uabooster
    @ResponseBody
    @GetMapping("/uabooster")
    public ResponseEntity<List<UnionArenaBooster>> getAllUABooster() {
        return new ResponseEntity<List<UnionArenaBooster>>(uaBoosterService.allBooster(), HttpStatus.OK);
    }

    // http//localhost:8080/yyt
    @ResponseBody
    @GetMapping("/yyt")
    public ResponseEntity<List<CardPriceYYT>> getAllYuyutei() {
        return new ResponseEntity<List<CardPriceYYT>>(yuyuteiService.allCards(), HttpStatus.OK);
    }

    // http//localhost:8080/yyt/{priceYytId}
    @ResponseBody
    @GetMapping("/yyt/{priceYytId}")
    public ResponseEntity<Optional<CardPriceYYT>> getOneYYTCard(@PathVariable String priceYytId) {
        return new ResponseEntity<Optional<CardPriceYYT>>(yuyuteiService.findCardByPriceYytId(priceYytId),
                HttpStatus.OK);
    }

    // http//localhost:8080/fulla
    @ResponseBody
    @GetMapping("/fulla")
    public ResponseEntity<List<CardPriceFULLA>> getAllFulla() {
        return new ResponseEntity<List<CardPriceFULLA>>(fullaheadService.allFHCards(), HttpStatus.OK);
    }

    // http//localhost:8080/fulla/{priceFullaId}
    @ResponseBody
    @GetMapping("/fulla/{priceFullaId}")
    public ResponseEntity<Optional<CardPriceFULLA>> getOneFullaCard(@PathVariable String priceFullaId) {
        return new ResponseEntity<Optional<CardPriceFULLA>>(fullaheadService.findCardByPriceFullaId(priceFullaId),
                HttpStatus.OK);
    }

}