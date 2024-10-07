package com.geekstack.cards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.UnionArenaCard;
import com.geekstack.cards.repository.UnionArenaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UnionArenaService{

    @Autowired
    private UnionArenaRepository unionArenaRepository;

    public List<UnionArenaCard> allUnionArena(){
        return unionArenaRepository.findAll();
    }

    public Optional<UnionArenaCard> findCardByCardUid(String cardUid) {
        return unionArenaRepository.findCardByCardUid(cardUid);
    }

}