package com.geekstack.cards.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "unionarenaBooster")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnionArenaBooster {

    private String _id;
    private String animecode;
    private String currentAnime;
    private List<String> listofboosters;
    private List<String> listofcolors;
    private List<String> listofrarities;

}
