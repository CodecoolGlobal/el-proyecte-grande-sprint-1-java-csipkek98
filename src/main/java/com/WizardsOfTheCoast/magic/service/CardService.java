package com.WizardsOfTheCoast.magic.service;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Service
public class CardService {

    public List<String> cardPictures(String pageNumber){
        ArrayList<String> imageUrls = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        Object cards = restTemplate.getForObject(CardEndpoints.CARD_PICTURES.getPath(), Object.class, pageNumber);
        String jsonInString = new Gson().toJson(cards);
        JSONObject mJSONObject = new JSONObject(jsonInString);
        JSONArray obj = mJSONObject.getJSONArray("cards");
        for (int i = 0; i < obj.length(); i++) {
            Set<String> asd = obj.getJSONObject(i).keySet();
            if(asd.contains("imageUrl")){
                System.out.println(obj.getJSONObject(i).getString("imageUrl"));
                imageUrls.add(obj.getJSONObject(i).getString("imageUrl"));
            }else
                System.out.println("no image");
        }
        return imageUrls;
    }

}
