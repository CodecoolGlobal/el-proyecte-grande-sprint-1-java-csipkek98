package com.WizardsOfTheCoast.magic.Data_sample;

import com.WizardsOfTheCoast.magic.model.CardModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomCardCreator {

    public static List<CardModel> initialize() {
        List<CardModel> cards = new ArrayList<>();
        CardModel card = new CardModel.CardBuilder("Mr.T the Humble", "Test id1", "https://i.im.ge/2022/06/16/rJGMJY.jpg" ,"1000").build();
        CardModel card2 = new CardModel.CardBuilder("Bob the Almighty", "Test id2", "https://i.im.ge/2022/06/16/rJGdnr.jpg", "2000").build();
        cards.add(card2);
        cards.add(card);
        return cards;
    }


}
