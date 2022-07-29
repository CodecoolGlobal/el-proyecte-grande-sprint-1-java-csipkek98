package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.model.CardModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        String parameterString = String.join("+",parameters);
        List<String> param = new ArrayList<>();
        param.add(parameterString);

        int cardsOnPageNumber = 0;
        ArrayList<CardModel> cardDetails = new ArrayList<>();
        JSONArray obj = converter.getJSONArray("data", param,endpoint);
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
            if(cardsOnPageNumber == 12){
                break;
            }
        }
        return cardDetails;
    }

    public CardModel getCard(APIEndpoints endpoint) {
        JSONObject obj = converter.getJSONObject(endpoint);
        Set<String> currencyKey = obj.getJSONObject("prices").keySet();
        CardModel card = null;
        if (currencyKey.contains("usd") || currencyKey.contains("eur")) {
            try{
                card = new CardModel.CardBuilder(
                        obj.getString("name"),
                        obj.getString("id"),
                        obj.getJSONObject("image_uris").getString("normal"),
                        obj.getJSONObject("prices").getString("usd")).build();
            }catch (JSONException e){
                return getCard(endpoint);
            }
        }
        return card;
    }
}
