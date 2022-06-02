package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.service.APIEndpoints;
import com.WizardsOfTheCoast.magic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RandomCardController {

    private CardService cardService;

    @Autowired
    public void setService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/randcomcard")
    public String getRandomCard(Model model){
        List<String> parameters = new ArrayList<>();
        model.addAttribute("filteredCards",cardService.getCards(parameters, APIEndpoints.RANDOM));
        return "carddetails";
    }
}
