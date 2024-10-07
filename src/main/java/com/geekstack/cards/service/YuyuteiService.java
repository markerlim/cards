package com.geekstack.cards.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.CardPriceYYT;
import com.geekstack.cards.repository.YuyuteiRepository;

@Service
public class YuyuteiService {
    
    @Autowired
    private YuyuteiRepository yuyuteiRepository;

    public List<CardPriceYYT> allCards() {
        return yuyuteiRepository.findAll();
    }

    public Optional<CardPriceYYT> findCardByPriceYytId(String priceYytId) {
        return yuyuteiRepository.findCardByPriceYytId(priceYytId);
    }
}
