package com.geekstack.cards.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "dragonballzfw")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DragonBallzFWCard {

    @Id
    private ObjectId _id;
    private boolean awakenform;

    @Field("booster")
    private String booster;
    private String cardId;
    @Field("cardUid")
    private String cardUid;

    private String cardName;
    @Field("cardNameLower")
    @TextIndexed
    private String cardNameLower;
    private String cardtype;
    private String color;
    private String combopower;
    private String cost;
    private String effects;
    private String features;
    private String image;
    private String power;
    private String rarityAct;
    private String rarity;
    private String setFrom;
    private String specifieddcost;
    private String urlimage;

}
