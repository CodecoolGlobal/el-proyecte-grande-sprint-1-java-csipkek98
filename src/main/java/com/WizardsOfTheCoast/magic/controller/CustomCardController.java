package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.service.customCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;



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
    public List<CustomCardEntity> Greetings(){

        return cardService.getAllCustomCard();
    }

    @GetMapping(value = "/custom")
    public List<CustomCardEntity> getClients() {

        return cardService.getAllCustomCard();
    }

    @PostMapping(value = "/custom")
    public CustomCardEntity addCustomCard(@RequestBody Map<String, Object> payLoad) {
        CustomCardEntity customCard = CustomCardEntity.builder()
                .name((String)payLoad.get("name"))
                .imageUrl((String)payLoad.get("pic"))
                .price(Integer.parseInt((String)payLoad.get("price")))
        .build();
        long l=Long.parseLong((String) payLoad.get("sessionId"));
        cardService.addCard(customCard);
        return customCard;
    }


    @DeleteMapping(value = "/custom/{id}")
    public void deleteCustomCard(@PathVariable String id){
        cardService.deleteCustomCardById(Long.parseLong(id));
    }

    @GetMapping(value = "custom/{id}/{name}")
    public void findCustomCardByName(@PathVariable String id, @PathVariable String name){
        //TODO implement without collection
    }

}
