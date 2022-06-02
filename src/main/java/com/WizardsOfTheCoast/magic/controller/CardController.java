package com.WizardsOfTheCoast.magic.controller;


import com.WizardsOfTheCoast.magic.service.APIEndpoints;
import com.WizardsOfTheCoast.magic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CardController {

    private CardService cardService;

    @Autowired
    public void setService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/{name}")
    public String getAllCardsTest(Model model, @PathVariable String name){
        List<String> parameters = new ArrayList<>();
        parameters.add(name);
        model.addAttribute("cards",cardService.getCards(parameters, APIEndpoints.SEARCH));
        return "index";
    }

}
