package com.geekstack.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekstack.cards.model.BoosterButton;
import com.geekstack.cards.model.UnionArenaBooster;
import com.geekstack.cards.service.BoosterListService;
import com.geekstack.cards.service.UABoosterService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "http://localhost:3000" })
@RestController
@RequestMapping("/api/boosterlist")
public class BoosterController {

    @Autowired
    private UABoosterService uaBoosterService;
    @Autowired
    private BoosterListService boosterListService;


    @GetMapping("/{tcg}")
    public ResponseEntity<List<BoosterButton>> getUAList(@PathVariable String tcg){
        return new ResponseEntity<List<BoosterButton>>(boosterListService.allBoosterByTcg(tcg),HttpStatus.OK);
    }

    @GetMapping("/unionarena/{animecode}")
    public ResponseEntity<List<UnionArenaBooster>> getAllUABooster(@PathVariable String animecode) {
        
        return new ResponseEntity<List<UnionArenaBooster>>(uaBoosterService.allBooster(), HttpStatus.OK);
    }

}
