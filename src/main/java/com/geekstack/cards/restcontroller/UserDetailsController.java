package com.geekstack.cards.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geekstack.cards.model.CookieRunDecklist;
import com.geekstack.cards.model.DragonballzFWDecklist;
import com.geekstack.cards.model.OnePieceDecklist;
import com.geekstack.cards.model.UnionArenaDecklist;
import com.geekstack.cards.repository.UserDetailsMongoRepository;
import com.geekstack.cards.service.UserDetailService;

@RestController
@RequestMapping("/api/user")
public class UserDetailsController {

    @Autowired
    private UserDetailsMongoRepository userDetailsMongoRepository;

    @Autowired
    private UserDetailService userDetailService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createNewUser(@RequestBody String payload) {
        try {
            System.out.println(payload);
            Map<String, Object> response = new HashMap<>();
            if (userDetailService.createUser(payload) == 1) {
                response.put("message", "User created successfully");
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
            response.put("message", "Exit");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error adding user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Union Arena save user deck endpoint
    @PostMapping("/save/unionarena/{userId}/deck")
    public ResponseEntity<Map<String, Object>> saveUADeck(@PathVariable String userId,
            @RequestBody UnionArenaDecklist decklist) {
        try {
            userDetailsMongoRepository.createUnionArenaDecklist(decklist, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Deck created successfully");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error adding deck: " + e.getMessage());
            response.put("deckId", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Union Arena load user deck endpoint
    @GetMapping("/load/unionarena/{userId}/deck")
    public ResponseEntity<List<UnionArenaDecklist>> loadUADeck(@PathVariable String userId) {
        return new ResponseEntity<List<UnionArenaDecklist>>(userDetailsMongoRepository.loadUnionArenaDecklist(userId),
                HttpStatus.OK);
    }

    // One Piece save user deck endpoint
    @PostMapping("/save/onepiece/{userId}/deck")
    public ResponseEntity<Map<String, Object>> saveOPDeck(@PathVariable String userId,
            @RequestBody OnePieceDecklist decklist) {
        try {
            userDetailsMongoRepository.createOnePieceDecklist(decklist, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Deck created successfully");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error adding deck: " + e.getMessage());
            response.put("deckId", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // One Piece load user deck endpoint
    @GetMapping("/load/onepiece/{userId}/deck")
    public ResponseEntity<List<OnePieceDecklist>> loadOPDeck(@PathVariable String userId) {
        return new ResponseEntity<List<OnePieceDecklist>>(userDetailsMongoRepository.loadOnePieceDecklist(userId),
                HttpStatus.OK);
    }

    // DragonballzFW save user deck endpoint
    @PostMapping("/save/dragonballzfw/{userId}/deck")
    public ResponseEntity<Map<String, Object>> saveDBZFWDeck(@PathVariable String userId,
            @RequestBody DragonballzFWDecklist decklist) {
        try {
            userDetailsMongoRepository.createDragonballzFWDecklist(decklist, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Deck created successfully");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error adding deck: " + e.getMessage());
            response.put("deckId", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // DragonballzFW load user deck endpoint
    @GetMapping("/load/dragonballzfw/{userId}/deck")
    public ResponseEntity<List<DragonballzFWDecklist>> loadDBZFWDeck(@PathVariable String userId) {
        return new ResponseEntity<List<DragonballzFWDecklist>>(
                userDetailsMongoRepository.loadDragonballzFWDecklist(userId), HttpStatus.OK);
    }

    // DragonballzFW save user deck endpoint
    @PostMapping("/save/cookierunbraverse/{userId}/deck")
    public ResponseEntity<Map<String, Object>> saveCRBDeck(@PathVariable String userId,
            @RequestBody CookieRunDecklist decklist) {
        try {
            userDetailsMongoRepository.createCookieRunDecklist(decklist, userId);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Deck created successfully");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error adding deck: " + e.getMessage());
            response.put("deckId", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // Cookierunbraverse load user deck endpoint
    @GetMapping("/load/cookierunbraverse/{userId}/deck")
    public ResponseEntity<List<CookieRunDecklist>> loadCRBDeck(@PathVariable String userId) {
        return new ResponseEntity<List<CookieRunDecklist>>(userDetailsMongoRepository.loadCookieRunDecklist(userId),
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{tcg}/{userId}/deck/{deckId}")
    public ResponseEntity<Map<String, Object>> deleteDeck( @PathVariable String tcg,@PathVariable String userId,
            @PathVariable String deckId) {
        try {
            userDetailsMongoRepository.deleteDecklist(tcg, userId, deckId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Deck deleted successfully");

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error adding deck: " + e.getMessage());
            response.put("deckId", deckId);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
