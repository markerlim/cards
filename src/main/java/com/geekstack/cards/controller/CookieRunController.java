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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geekstack.cards.model.CookieRunCard;
import com.geekstack.cards.service.CookieRunService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:8080", "http://localhost:3000" })
@RestController
@RequestMapping("/api/cookirunbraverse")
public class CookieRunController {

    @Autowired
    private CookieRunService cookieRunService;

    @GetMapping("")
    public ResponseEntity<Page<CookieRunCard>> getAllDragonballzfw(
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "cardNo", required = false) String cardNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        // Create a Pageable object for pagination
        Pageable pageable = PageRequest.of(page, size,
                Sort.by("boostercode").ascending().and(Sort.by("field_cardNo_suyeowsc").ascending()));
        Page<CookieRunCard> pagedCards;

        // Perform filtering based on the presence of query parameters
        if (query != null && !query.isEmpty()) {
            // Perform full-text search using the query parameter
            pagedCards = cookieRunService.searchCookieRunByText(query, pageable);
        } else {
            if (cardNo != null && !cardNo.isEmpty()) {
                pagedCards = cookieRunService.findAllByCardNo(cardNo, pageable);
            } else {
                // No filters, return all cards with pagination
                pagedCards = cookieRunService.allCookieRun(pageable);
            }
        }

        return new ResponseEntity<>(pagedCards, HttpStatus.OK);
    }

}
