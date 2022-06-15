package com.WizardsOfTheCoast.magic.DAO;
import com.WizardsOfTheCoast.magic.model.CardModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component("customCardDao")
public class CustomCardInMem implements CustomCardDAO {

    private List<CardModel> customCards = new ArrayList<>();

    @Override
    public void addCustomCard(CardModel customCard) {
        customCards.add(customCard);
    }

    @Override
    public List<CardModel> getAllCustomCard() {
        return customCards;
    }
}
