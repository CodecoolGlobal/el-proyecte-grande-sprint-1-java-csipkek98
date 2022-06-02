package com.WizardsOfTheCoast.magic.service;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class JsonArrayConverter {

    public JSONArray getJSONArray(String endPointKey, List<String> parameters, APIEndpoints apiEndpoints){
        RestTemplate restTemplate = new RestTemplate();
        Object cardsObject = null;
        switch (apiEndpoints) {
            case FILTER -> cardsObject = restTemplate.getForObject(apiEndpoints.getPath(), Object.class, parameters.get(0), parameters.get(1));
            case SEARCH -> cardsObject = restTemplate.getForObject(apiEndpoints.getPath(), Object.class, parameters.get(0));
            case RANDOM -> cardsObject = restTemplate.getForObject(apiEndpoints.getPath(), Object.class);
            default -> {
            }
        }

        String jsonInString = new Gson().toJson(cardsObject);
        JSONObject mJSONObject = new JSONObject(jsonInString);
        return mJSONObject.getJSONArray(endPointKey);
    }

    public JSONObject getJSONObject(APIEndpoints apiEndpoints ){
        RestTemplate restTemplate = new RestTemplate();
        Object cardsObject = null;
        cardsObject = restTemplate.getForObject(apiEndpoints.getPath(), Object.class);
        String jsonInString = new Gson().toJson(cardsObject);
        return new JSONObject(jsonInString);
    }

}
