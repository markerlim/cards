package com.geekstack.cards.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import com.geekstack.cards.model.UnionArenaCard;

import static com.geekstack.cards.utils.Constants.*;

@Repository
public class CL_UnionArenaRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UnionArenaCard> getCards() {
        return mongoTemplate.findAll(UnionArenaCard.class);
    }

    /* Return cards by anime code */
    public List<UnionArenaCard> getCardsByAnimeCode(String animecode) {
        Criteria criteria = Criteria.where(F_ANIMECODE).is(animecode);
        Query query = new Query(criteria);

        QuerySorting(query, F_CARDUID, true);

        List<UnionArenaCard> results = mongoTemplate.find(query, UnionArenaCard.class, C_UNIONARENA);
        return results;
    }

    /*
     * Search for cards via text index set under the Model, reference model for
     * which text are indexed
     */
    public List<UnionArenaCard> searchForCards(String term) {
        TextCriteria textCriteria = TextCriteria.forDefaultLanguage()
                .matchingPhrase(term);

        TextQuery textQuery = TextQuery.queryText(textCriteria);

        TextQuerySorting(textQuery, F_BOOSTER, true, F_CARDUID, true);

        List<UnionArenaCard> results = mongoTemplate.find(textQuery, UnionArenaCard.class, C_UNIONARENA);
        return results;
    }

    public List<String> getDistinctBooster(String animecode) {

        Criteria criteria = Criteria.where(F_ANIMECODE).is(animecode);
        Query query = new Query(criteria);
        query.fields().include(F_BOOSTER);

        return mongoTemplate.findDistinct(query, F_BOOSTER, C_UNIONARENA, String.class);
    }

    public List<String> getDistinctColor(String animecode) {

        Criteria criteria = Criteria.where(F_ANIMECODE).is(animecode);
        Query query = new Query(criteria);
        query.fields().include(F_COLOR);

        return mongoTemplate.findDistinct(query, F_COLOR, C_UNIONARENA, String.class);
    }

    public List<String> getDistinctRarity(String animecode) {

        Criteria criteria = Criteria.where(F_ANIMECODE).is(animecode);
        Query query = new Query(criteria);
        query.fields().include(F_RARITY);


        return mongoTemplate.findDistinct(query, F_RARITY, C_UNIONARENA, String.class);
    }

}