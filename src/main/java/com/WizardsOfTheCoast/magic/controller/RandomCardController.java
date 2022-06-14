package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.model.CardModel;
import com.WizardsOfTheCoast.magic.service.APIEndpoints;
import com.WizardsOfTheCoast.magic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = {"http://localhost:3000","https://magictrade.azurewebsites.net"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class RandomCardController {

    private CardService cardService;

    @Autowired
    public void setService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/randomcard")
    @CrossOrigin("*")
    public CardModel getRandomCard(){
        CardModel cardData = cardService.getCard(APIEndpoints.RANDOM);
        while(cardData == null){
            cardData = cardService.getCard(APIEndpoints.RANDOM);
        }
        return cardData;
    }
}
