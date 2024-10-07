package com.geekstack.cards.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "unionarena")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnionArenaCard {

    @Id
    private ObjectId _id;
    private String anime;
    private int apcost;
    private int banRatio;
    private String banWith;
    private String basicpower;
    private String booster;
    private String cardId;
    private String cardUid;
    private String cardName;
    private String cardNameLower;
    private String category;
    private String color;
    private String effect;
    private int energycost;
    private String energygen;
    private String image;
    private String rarity;
    private String traits;
    private String trigger;
    private String triggerState;
    private String urlimage;
    private String rarityAct;
    private String cardcode;
    private String price_yyt_id;
    private String price_fulla_id;
    
}
