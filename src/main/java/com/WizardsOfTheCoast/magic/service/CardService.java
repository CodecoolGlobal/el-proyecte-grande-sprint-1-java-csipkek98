package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.model.CardModel;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public List<CardModel> cardPictures(List<String> parameters){
        ArrayList<CardModel> cardDetails = new ArrayList<>();
        JSONArray obj = converter.getJSONArray("data", parameters,APIEndpoints.FILTER);
        for (int i = 0; i < obj.length(); i++) {
            Set<String> asd = obj.getJSONObject(i).getJSONObject("prices").keySet();
            if(asd.contains("usd") || asd.contains("eur")){
                CardModel card = new CardModel.CardBuilder(
                        obj.getJSONObject(i).getString("name"),
                        obj.getJSONObject(i).getString("id"),
                        obj.getJSONObject(i).getJSONObject("image_uris").getString("normal"),
                        obj.getJSONObject(i).getJSONObject("prices").getString("usd")).build();
                cardDetails.add(card);
                System.out.println("There is price");
            }else
                System.out.println("no image");
        }
        return cardDetails;
    }

}
