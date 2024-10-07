package com.geekstack.cards.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.CardPriceFULLA;
import com.geekstack.cards.repository.FullaheadRepository;

@Service
public class FullaheadService {
    
    @Autowired
    private FullaheadRepository fullaheadRepository;

    public List<CardPriceFULLA> allFHCards() {
        return fullaheadRepository.findAll();
    }

    public Optional<CardPriceFULLA> findCardByPriceFullaId(String priceFullaId) {
        return fullaheadRepository.findCardByPriceFullaId(priceFullaId);
    }
}
