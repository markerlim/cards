package com.geekstack.cards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.OnePieceCard;
import com.geekstack.cards.repository.OnePieceRepository;

@Service
public class OnePieceService {

    @Autowired
    private OnePieceRepository onePieceRepository;

        public List<OnePieceCard> allOnePiecePage() {
        // Define sorting by booster and cardUid
        Sort sort = Sort.by("booster").ascending().and(Sort.by("cardUid").ascending());
        return onePieceRepository.findAll(sort);  // Fetch all sorted records from the repository
    }
}
