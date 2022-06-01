package com.WizardsOfTheCoast.magic.controller;


import com.WizardsOfTheCoast.magic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CardController {

    private CardService cardService;

    @Autowired
    public void setService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/")
    public String getAllCardsTest(Model model){
        model.addAttribute("cards",cardService.cardPictures("name=Pyroblast"));
        return "index";
    }

}
