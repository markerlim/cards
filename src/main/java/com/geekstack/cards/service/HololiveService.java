package com.geekstack.cards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.HololiveCard;
import com.geekstack.cards.repository.HololiveRepository;

@Service
public class HololiveService {

    @Autowired
    private HololiveRepository hololiveRepository;

    public List<HololiveCard> allHololivePage(){
        Sort sort = Sort.by("booster").ascending().and(Sort.by("cardUid").ascending());
        return hololiveRepository.findAll(sort);
    }

}
