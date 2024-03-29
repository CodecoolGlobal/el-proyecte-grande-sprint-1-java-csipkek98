package com.WizardsOfTheCoast.magic.service;


import com.WizardsOfTheCoast.magic.JPA.CustomCardRepository;
import com.WizardsOfTheCoast.magic.JPA.DeckRepository;
import com.WizardsOfTheCoast.magic.JPA.UserRepository;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DeckService {
    private DeckRepository deckRepository;
    private UserRepository userRepository;
    private CustomCardRepository customCardRepository;

    @Autowired
    public DeckService(DeckRepository deckRepository, UserRepository userRepository, CustomCardRepository customCardRepository) {
        this.deckRepository = deckRepository;
        this.userRepository = userRepository;
        this.customCardRepository = customCardRepository;
    }

    public DeckEntity findDeckById(Long id){
        return deckRepository.findById(id).get();
    }

    public void saveDeck(DeckEntity deck){
        deckRepository.save(deck);
    }
    public void deleteCardFromDeck(DeckEntity deck, Long cardId){
        CustomCardEntity cardToDelete = null;
        for (CustomCardEntity card : deck.getCards()) {
            if(Objects.equals(card.getId(), cardId)){
                cardToDelete = card;
            }
        }
        deck.removeCard(cardToDelete);
        assert cardToDelete != null;
        customCardRepository.delete(cardToDelete);
    }
}
