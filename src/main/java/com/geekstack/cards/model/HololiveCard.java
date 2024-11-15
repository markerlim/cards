package com.geekstack.cards.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "CL_hololiveocg")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HololiveCard {
    @Id
    private ObjectId _id;
    
    private String cardId;

    @Field("cardUid")
    private String cardUid;
    private String cardNameJP;
    private String cardNameEN;
    private String cardNameEN_lower;
    private String type;
    private String rarity;
    private String color;
    private String lifehp;
    private String tags;
    private String text;
    private String image;

    @Field("booster")
    private String booster;
    private String setfrom;

}
