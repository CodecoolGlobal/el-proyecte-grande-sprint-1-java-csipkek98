package com.WizardsOfTheCoast.magic.controller;


import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.service.DeckService;
import com.WizardsOfTheCoast.magic.service.UserService;
import com.WizardsOfTheCoast.magic.service.CustomCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net", "http://localhost:4200"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class DeckController {

    private UserService userService;
    private CustomCardService customCardService;
    private DeckService deckService;


    public DeckController(UserService userService,
                          CustomCardService customCardService,
                          DeckService deckService) {
        this.userService = userService;
        this.customCardService = customCardService;
        this.deckService = deckService;
    }

    @GetMapping(value = "/decks/{id}")
    public List<DeckEntity> getAllUserDecks(@PathVariable String id){
        User user = userService.findUserById(Long.valueOf(id));
        return userService.getAllUserDecks(Long.valueOf(id));
    }

    @GetMapping(value ="/decks/cards/user/{id}/deck/{deckid}")
    public List<CustomCardEntity> getAllDeckCards(@PathVariable String id, @PathVariable String deckid)
    {
        User user = userService.findUserById(Long.valueOf(id));
        DeckEntity deck = user.findDeckById(Long.valueOf(deckid));
        for (Object deckCard : deck.getCards()) {
            System.out.println(deckCard.toString());
        }
        return deck.getCards();
    }

    @PostMapping(value ="/decks/add_card_to_deck")
    public DeckEntity addCardToDeck(@RequestBody Map<String, Object> payLoad){
        Long id = Long.parseLong((String) payLoad.get("deckId"));;
        String name = (String) payLoad.get("name");
        DeckEntity deck = deckService.findDeckById(id);
        CustomCardEntity card = customCardService.findCardByName(name);
        deck.addCard(card);
        customCardService.saveCard(card);
        return deck;
    }

    @PostMapping(value="/decks/add_deck")
        public DeckEntity addNewDeck(@RequestBody Map<String, Object> payLoad){
            Long id = Long.parseLong((String) payLoad.get("user_id"));
            User user = userService.findUserById(id);
            DeckEntity newDeck = new DeckEntity();
            user.addDeck(newDeck);
            newDeck.setUser(user);
            userService.saveUser(user);
            deckService.saveDeck(newDeck);
            return newDeck;
        }
}
