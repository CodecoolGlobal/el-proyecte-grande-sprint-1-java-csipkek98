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
        JSONArray obj = converter.getJSONArray("data",pageNumber);
        for (int i = 0; i < obj.length(); i++) {
            imageUrls.add(obj.getJSONObject(i).getJSONObject("image_uris").getString("normal"));
        }
        return imageUrls;
    }

}
