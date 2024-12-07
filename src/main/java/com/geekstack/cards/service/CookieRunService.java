package com.geekstack.cards.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Service;

import com.geekstack.cards.model.CookieRunCard;
import com.geekstack.cards.repository.CookieRunRepository;

@Service
public class CookieRunService {

    @Autowired
    private CookieRunRepository cookieRunRepository;

    public Page<CookieRunCard> allCookieRun(Pageable pageable) {
        return cookieRunRepository.findAll(pageable);
    }

    public List<CookieRunCard> allCookieRunPage() {
        Sort sort = Sort.by("boostercode").ascending().and(Sort.by("id").ascending());
        return cookieRunRepository.findAll(sort);
    }

    public Page<CookieRunCard> searchCookieRunByText(String searchTerm, Pageable pageable) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(searchTerm);
        return cookieRunRepository.findAllBy(criteria, pageable);
    }

    public Page<CookieRunCard> findAllByCardNo(String cardNo, Pageable pageable) {
        return cookieRunRepository.findAllByCardNo(cardNo, pageable);
    }

    public Page<CookieRunCard> findByBoostercode(String boostercode, Pageable pageable) {
        return cookieRunRepository.findByBoostercode(boostercode, pageable);
    }
}
