package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.model.CardModel;
import com.WizardsOfTheCoast.magic.service.APIEndpoints;
import com.WizardsOfTheCoast.magic.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class FilterController {
    private CardService cardService;

    @Autowired
    public void setService(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/card/filter")
    public List<CardModel> getCardsByFilters(HttpServletRequest request, Model model) {
        String query = request.getQueryString();
        System.out.println(query);
        String[] parameters = query.split("[&=]");
        List<String> parameterData = new ArrayList<>();
        for (int i = 0; i < parameters.length; i = i + 3) {
            System.out.println(parameters[i] + " " + parameters[i]);
            parameterData.add(parameters[i] + "=" + parameters[i + 1]);
        }
        System.out.println(parameterData);
        return cardService.getCards(parameterData, APIEndpoints.SEARCH);
    }
}
