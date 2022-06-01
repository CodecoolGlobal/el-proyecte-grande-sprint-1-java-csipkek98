package com.WizardsOfTheCoast.magic.service;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class JsonArrayConverter {

    public JSONArray getJSONArray(String endPointKey, String parameters){
        RestTemplate restTemplate = new RestTemplate();
        Object cardsObject = restTemplate.getForObject(APIEndpoints.SEARCH.getPath(), Object.class, parameters);
        String jsonInString = new Gson().toJson(cardsObject);
        JSONObject mJSONObject = new JSONObject(jsonInString);

        return mJSONObject.getJSONArray(endPointKey);
    }

}
