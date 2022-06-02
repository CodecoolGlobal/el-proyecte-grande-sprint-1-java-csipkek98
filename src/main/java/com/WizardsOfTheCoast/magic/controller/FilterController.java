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
public class FilterController {
    private CardService cardService;

    @Autowired
    public void setService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card/filter/{rarity}/{rarityType}")
    public String getCardsByRarity(Model model, @PathVariable String rarity, @PathVariable String rarityType){
        List<String> parameters = new ArrayList<>();
        parameters.add(rarity);
        parameters.add(rarityType);
        model.addAttribute("filteredCards",cardService.getCards(parameters, APIEndpoints.FILTER));
        return "filter";
    }
}
