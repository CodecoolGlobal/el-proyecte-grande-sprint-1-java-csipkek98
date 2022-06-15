package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.Data_sample.CustomCardCreator;
import com.WizardsOfTheCoast.magic.model.CardModel;
import com.WizardsOfTheCoast.magic.service.CardService;
import com.WizardsOfTheCoast.magic.service.customCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;


@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net", "http://localhost:4200"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class CustomCardController {
    private customCardService cardService;

    @Autowired
    public void setService(customCardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/")
    public void Greetings(){
        initCards();
        for(CardModel card: cardService.getAllCustomCard()){
            System.out.println(card.getName());
        }
    }

    @PostMapping
    public void createClient(@RequestBody CardModel customCard) throws URISyntaxException {
        cardService.addCard(customCard);
    }

    @GetMapping(value = "/custom")
    public List<CardModel> getClients() {
        if (cardService.getAllCustomCard().size() < 2) {
            initCards();
        }
        return cardService.getAllCustomCard();
    }

    public void initCards(){
        List<CardModel> initCards = CustomCardCreator.initialize();
        for (CardModel initCard : initCards) {
            cardService.addCard(initCard);
        }
    }
}
