package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.model.CardModel;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public class CardService {

    private JsonArrayConverter converter;
    @Autowired
    public void setService(JsonArrayConverter converter) {
        this.converter = converter;
    }

        //TODO
        //Find out if the getCards method belong to this class or not
    public List<CardModel> getCards(List<String> parameters, APIEndpoints endpoint){
        int cardsOnPageNumber = 0;
        ArrayList<CardModel> cardDetails = new ArrayList<>();
        JSONArray obj = converter.getJSONArray("data", parameters,endpoint);
        for (int i = 0; i < obj.length(); i++) {
            Set<String> currencyKey = obj.getJSONObject(i).getJSONObject("prices").keySet();
            if(currencyKey.contains("usd") || currencyKey.contains("eur")){
                CardModel card = new CardModel.CardBuilder(
                        obj.getJSONObject(i).getString("name"),
                        obj.getJSONObject(i).getString("id"),
                        obj.getJSONObject(i).getJSONObject("image_uris").getString("normal"),
                        obj.getJSONObject(i).getJSONObject("prices").getString("usd")).build();
                cardDetails.add(card);
                cardsOnPageNumber++;

            }
            if(cardsOnPageNumber == 10){
                break;
            }
        }
        return cardDetails;
    }

    public CardModel getCard(APIEndpoints endpoint){

    }
}
