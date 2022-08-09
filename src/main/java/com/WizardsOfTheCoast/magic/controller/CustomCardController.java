package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.service.DeckService;
import com.WizardsOfTheCoast.magic.service.UserService;
import com.WizardsOfTheCoast.magic.service.CustomCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;



@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net", "http://localhost:4200"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class CustomCardController {
    private CustomCardService cardService;
    private UserService userService;
    private DeckService deckService;

    @Autowired
    public CustomCardController(CustomCardService cardService, UserService userService, DeckService deckService) {
        this.cardService = cardService;
        this.userService = userService;
        this.deckService = deckService;
    }


    @GetMapping(value = "/")
    public List<CustomCardEntity> Greetings(){

        return cardService.getAllCustomCard();
    }

    @GetMapping(value = "/custom/{id}")
    public List<CustomCardEntity> getClients(@PathVariable String id) {
        System.out.println(id);
        User user = userService.findUserById(Long.valueOf(id));
        return userService.getAllUserCards(Long.valueOf(id));
    }

    @PostMapping(value = "/custom")
    public ResponseEntity<CustomCardEntity> addCustomCard(
            @RequestBody @Valid Map<String, Object> payLoad, UriComponentsBuilder uriComponentsBuilder) {
        CustomCardEntity customCard = CustomCardEntity.builder()
                .name((String)payLoad.get("name"))
                .imageUrl((String)payLoad.get("pic"))
                .price(Integer.parseInt((String)payLoad.get("price")))
        .build();
        long l=Long.parseLong((String) payLoad.get("sessionId"));
        User user = userService.findUserById(l);
        user.addCard(customCard);
        customCard.setUser(user);
        cardService.saveCard(customCard);
        URI location = uriComponentsBuilder.path("/custom/search/{id}/{name}")
                .buildAndExpand(user.getId(), customCard.getName()).toUri();
        return ResponseEntity.created(location).body(customCard);
    }


    @DeleteMapping(value = "/custom/user/{user_id}/card_id/{id}")
    public void deleteCustomCard(@PathVariable String id, @PathVariable String user_id){
        CustomCardEntity cardToDelete = cardService.findCardById(Long.valueOf(id));
        DeckEntity deck = userService.checkIfCardInAnyDeck(Long.valueOf(user_id), cardToDelete);
        if(deck != null){
            deckService.deleteCardFromDeck(deck, Long.valueOf(id));
        }
        else{
            cardService.deleteCustomCardById(Long.parseLong(id));
        }
    }

    @GetMapping(value = "/custom/search/{id}/{name}")
    public ResponseEntity<CustomCardEntity>  findCustomCardByName(@PathVariable String id, @PathVariable String name){
        User user = userService.findUserById(Long.valueOf(id));
        return ResponseEntity.ok().body(user.findCustomCardByName(name));
    }

}
