package com.geekstack.cards.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextQuery;

public class Constants {
    
    public static final String C_UNIONARENA="CL_unionarena_v2";
    public static final String C_DUELMASTER="CL_duelmaster";
    public static final String C_ONEPIECE="CL_onepiece";
    public static final String C_DRAGONBALLZFW="CL_dragonballzfw";
    public static final String C_COOKIERUN="CL_cookierunbraverse";
    public static final String C_FULLAHEAD="cardprices_fulla";
    public static final String C_YUYUTEI="cardprices_yyt";

    public static final String F_BOOSTER="booster";
    public static final String F_ANIMECODE="animeCode";
    public static final String F_RARITY="rarity";
    public static final String F_COLOR="color";
    public static final String F_CATEGORY="category";
    public static final String F_CIVILIZATION="civilization";
    public static final String F_CARDTYPE="cardType";
    public static final String F_CARDUID="cardUid";
    public static final String F_CARDID="cardId";

    public static final String PRICE_FULLA ="price_fulla_id";
    public static final String PRICE_YYT ="price_yyt_id";


    public static void QuerySorting(Query query, String field, boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        query.with(Sort.by(direction, field));
    }

    public static void TextQuerySorting(TextQuery textQuery, String field, boolean isAscending) {
        Sort.Direction direction = isAscending ? Sort.Direction.ASC : Sort.Direction.DESC;
        textQuery.with(Sort.by(direction, field));
    }

    public static void TextQuerySorting(TextQuery textQuery, String field1, boolean isAscending1, String field2, boolean isAscending2) {
        Sort.Order order1 = new Sort.Order(isAscending1 ? Sort.Direction.ASC : Sort.Direction.DESC, field1);
        Sort.Order order2 = new Sort.Order(isAscending2 ? Sort.Direction.ASC : Sort.Direction.DESC, field2);
        textQuery.with(Sort.by(order1, order2));
    }
    

}
