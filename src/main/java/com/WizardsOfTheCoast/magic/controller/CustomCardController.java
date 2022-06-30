package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.Data_sample.CustomCardCreator;
import com.WizardsOfTheCoast.magic.entity.CollectionEntity;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.service.CollectionService;
import com.WizardsOfTheCoast.magic.service.customCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;



@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net", "http://localhost:4200"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class CustomCardController {
    private customCardService cardService;
    private CollectionService collectionService;

    public CustomCardController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @Autowired
    public void setService(customCardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping(value = "/")
    public List<CustomCardEntity> Greetings(){
//        initCards();
//        for(CustomCardEntity card: cardService.getAllCustomCard()){
//            System.out.println(card.getName() + "init");
//        }
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
        CollectionEntity collection = collectionService.getCollection(l);
        customCard.setCollection(collection);
        cardService.addCard(customCard);
        return customCard;
    }

    @PostMapping(value = "/custom/create")
    public CollectionEntity addCollection(){
        List<CustomCardEntity> empty = new ArrayList<>();
        CollectionEntity collection = CollectionEntity.builder()
                .cards(empty)
                .build();
        collectionService.saveCollection(collection);
        return collection;
    }

    @DeleteMapping(value = "/custom/{id}")
    public void deleteCustomCard(@PathVariable String id){
        cardService.deleteCustomCardById(Long.parseLong(id));
    }

    @GetMapping(value = "custom/{id}/{name}")
    public CustomCardEntity findCustomCardByName(@PathVariable String id, @PathVariable String name{
        return cardService.findCustomCardFromCollectionByName(Long.valueOf(id), name);
    }

    public void initCards(){
        List<CustomCardEntity> initCards = CustomCardCreator.initialize();
        for (CustomCardEntity initCard : initCards) {
            cardService.addCard(initCard);
        }
    }
}
