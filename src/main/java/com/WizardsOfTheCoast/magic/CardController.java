package com.WizardsOfTheCoast.magic;


import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Controller
public class CardController {


    @RequestMapping("/cards")
    public String cards(){
        return "Test cards";
    }

    @GetMapping(value = "/morecards")
    public Object getAllCards(){
        String url = "https://api.magicthegathering.io/v1/cards?pageSize=10";
        RestTemplate restTemplate = new RestTemplate();
        Object cards = restTemplate.getForObject(url, Object.class);
        String jsonInString = new Gson().toJson(cards);
        JSONObject mJSONObject = new JSONObject(jsonInString);
        JSONArray obj = mJSONObject.getJSONArray("cards");
        for (int i = 0; i < obj.length(); i++) {
            Set<String> asd = obj.getJSONObject(i).keySet();
            if(asd.contains("imageUrl")){
                System.out.println(obj.getJSONObject(i).getString("imageUrl"));
            }else
                System.out.println("no image");
        }


        return restTemplate.getForObject(url, Object.class);
    }

    @GetMapping(value = "/")
    public String getAllCardsTest(Model model){
        model.addAttribute("cards",getAllCards());
        System.out.println(getAllCards());
        return "index";
    }

}
