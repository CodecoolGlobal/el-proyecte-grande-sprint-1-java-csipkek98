package com.WizardsOfTheCoast.magic.service;

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

    public List<String> cardPictures(String pageNumber){
        ArrayList<String> imageUrls = new ArrayList<>();
        JSONArray obj = converter.getJSONArray("cards",pageNumber);
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
