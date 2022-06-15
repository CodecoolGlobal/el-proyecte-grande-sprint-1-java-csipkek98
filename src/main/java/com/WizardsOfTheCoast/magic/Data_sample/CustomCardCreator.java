package com.WizardsOfTheCoast.magic.Data_sample;

import com.WizardsOfTheCoast.magic.model.CardModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomCardCreator {

    public static List<CardModel> initialize() {
        List<CardModel> cards = new ArrayList<>();
        CardModel card = new CardModel.CardBuilder("Test card", "Test id1", "Test url", "1").build();
        CardModel card2 = new CardModel.CardBuilder("Test card3", "Test id2", "Test url", "2").build();
        cards.add(card2);
        cards.add(card);
        return cards;
    }


}
