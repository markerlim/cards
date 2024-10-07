package com.geekstack.cards.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "cardprices_yyt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardPriceYYT {

    @Id
    private ObjectId id;
    private float pricejpy;
    private String rarity;
    private String cardcode;
    private int stock;
    private String url;
    private String animecode;

    @Field("price_yyt_id")
    private String priceYytId;
    private Map<String, List<PriceData>> pricedata;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PriceData {
        private LocalDateTime date;
        private float pricejpydaily;
        private int stock; // Assuming stock is an integer
        private float priceChange;
    }
}
