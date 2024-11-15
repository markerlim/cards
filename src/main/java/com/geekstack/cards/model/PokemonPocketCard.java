package com.geekstack.cards.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "CL_pokemonpocket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokemonPocketCard {
    @Id
    private ObjectId _id;
    private String cardId;
    private String rarity;
    private String booster;
    private String cardname;
    private String element;
    private String hp;
    private String attackinfo;
    private String weakness;
    private String retreat;
    private String illustrator;
    private String urlimage;
}
