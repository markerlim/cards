package com.geekstack.cards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.DragonBallzFWCard;
import com.geekstack.cards.repository.DragonBallzFWRepository;

@Service
public class DragonBallzFWService {

    @Autowired
    private DragonBallzFWRepository dragonBallzFWRepository;

    public List<DragonBallzFWCard> allDragonballzFWPage() {
        // Define sorting by booster and cardUid
        Sort sort = Sort.by("booster").ascending().and(Sort.by("cardUid").ascending());
        return dragonBallzFWRepository.findAll(sort); // Fetch all sorted records from the repository
    }
}
