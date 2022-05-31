package com.WizardsOfTheCoast.magic.controller;


import com.WizardsOfTheCoast.magic.service.CardService;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class CardController {

    private CardService cardService;

    @Autowired
    public void setService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/")
    public String getAllCardsTest(Model model){
        model.addAttribute("cards",cardService.cardPictures("https://api.magicthegathering.io/v1/cards?pageSize=3"));
        return "index";
    }

}
