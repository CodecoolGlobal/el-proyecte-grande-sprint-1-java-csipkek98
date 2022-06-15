package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.Data_sample.CustomCardCreator;
import com.WizardsOfTheCoast.magic.model.CardModel;
import com.WizardsOfTheCoast.magic.service.customCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class CustomCardController {
    @Autowired
    private  customCardService service;

    @GetMapping(value = "/")
    public void Greetings(){
        initCards();
        for(CardModel card: service.getAllCustomCard()){
            System.out.println(card.getName());
        }
    }

    public void initCards(){
        List<CardModel> initCards = CustomCardCreator.initialize();
        for (CardModel initCard : initCards) {
            service.addCard(initCard);
        }
    }
}
