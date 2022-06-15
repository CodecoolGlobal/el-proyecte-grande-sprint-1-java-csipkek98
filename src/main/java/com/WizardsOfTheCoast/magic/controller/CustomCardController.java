package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.service.customCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class CustomCardController {

    private final customCardService service;

    @Autowired
    public CustomCardController(customCardService service) {
        this.service = service;
    }
}
