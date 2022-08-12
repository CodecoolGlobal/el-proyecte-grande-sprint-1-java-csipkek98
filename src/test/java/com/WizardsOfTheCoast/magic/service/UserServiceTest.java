package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.*;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private CustomCardRepository customCardRepository;
    @Mock
    private DeckRepository deckRepository;
    @Mock
    private static PasswordEncoder password;

    @InjectMocks
    private UserService userService;
    private List<DeckEntity> decks;
    private List<CustomCardEntity> cards;
    private User user;
    private CustomCardEntity card1;
    private DeckEntity deck1;
    private List<CustomCardEntity> cardsInDeck;
    @BeforeEach
    public void setup(){
        decks = new ArrayList<>();
        cards = new ArrayList<>();
        cardsInDeck = new ArrayList<>();
        user = User.builder()
                .id(1L)
                .name("Test")
                .username("TestUsername")
                .email("TestEmail")
                .password(password.encode("TestPassword"))
                .decks(decks)
                .cards(cards)
                .build();
        card1 = CustomCardEntity.builder()
                .id(1L)
                .name("TestCard")
                .price(100)
                .build();
        card1.setUser(user);
        user.addCard(card1);
        deck1 = DeckEntity.builder()
                .id(1L)
                .cards(cardsInDeck)
                .build();
        deck1.addCard(card1);
        user.addDeck(deck1);
    }

    @Test
    void getAllUserCardsTest(){
        CustomCardEntity card2 = new CustomCardEntity();
        List <CustomCardEntity> cards = user.getCards();
        cards.add(card2);
        Assertions.assertEquals(2, cards.size());
    }

    @Test
    void addCardToUserDeck() {
        deck1.addCard(card1);
        Assertions.assertNotNull(deck1.getCards().get(0));
        Assertions.assertEquals("TestCard", deck1.getCards().get(0).getName());
    }

    @Test
    void findUserById() {
    }

    @Test
    void findDeckById() {

    }

    @Test
    void checkIfCardInAnyDeck() {

        List<DeckEntity> decks = user.getDecks();
        DeckEntity destinationDeck = new DeckEntity();
        for (DeckEntity deck : decks) {
            for(CustomCardEntity card: deck.getCards()){
                if (card == card1) {
                    destinationDeck = deck;
                    break;
                }
            }
        }
        Assertions.assertNotNull(destinationDeck);
        Assertions.assertEquals(1L, destinationDeck.getId());
    }

    @Test
    void saveUserForDeck() {
    }

    @Test
    void findCustomCardByNameFromUser() {
        String cardToFind = "TestCard";
        userService.findCustomCardByNameFromUser(cardToFind, user);
        Assertions.assertNotNull(card1);
        Assertions.assertEquals("TestCard", card1.getName());
    }
}