package com.geekstack.cards.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "onepiece")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnePieceCard {
    
    @Id
    private ObjectId _id;

    @Field("booster")
    private String booster;
    private String cardfrom;
    private String cardname;

    @Field("cardname_lower")
    @TextIndexed
    private String cardname_lower;
    private String cardId;

    @Field("cardUid")
    private String cardUid;
    private String rarity;
    private String category;
    private String costlife;
    private String attribute;
    private String power;
    private String counter;
    private String color;
    private String typing;
    private String typing_lower;
    private String effects;
    private String trigger;
    private String image;
    private String urlimage;


}
