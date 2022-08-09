package com.WizardsOfTheCoast.magic.Data_sample;

import com.WizardsOfTheCoast.magic.JPA.CustomCardRepository;
import com.WizardsOfTheCoast.magic.JPA.DeckRepository;
import com.WizardsOfTheCoast.magic.JPA.MagicWalletRepository;
import com.WizardsOfTheCoast.magic.JPA.UserRepository;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import com.WizardsOfTheCoast.magic.entity.MagicWallet;
import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private CustomCardRepository customCardRepository;
    private UserRepository userRepository;
    private MagicWalletRepository magicWalletRepository;
    private DeckRepository deckRepository;
    private UserService userService;

    @Autowired
    public SampleDataLoader(CustomCardRepository customCardRepository,
                            UserRepository userRepository, MagicWalletRepository magicWalletRepository, DeckRepository deckRepository, UserService userService) {
        this.customCardRepository = customCardRepository;
        this.userRepository = userRepository;
        this.magicWalletRepository = magicWalletRepository;
        this.deckRepository = deckRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        int generatedCardNumber = 20;
        String [] randomNames = {"SpookySkeleton", "BoogeyMan", "BigBadWolf", "ScaryGhost", "TerrificTroll"};
        String [] randomPictures = {"https://www.beholder.hu/pic/galeria/0497.jpg",
                "https://www.beholder.hu/pic/galeria/0465.jpg",
                "https://www.beholder.hu/pic/galeria/0464.jpg",
                "https://www.beholder.hu/pic/galeria/0451.jpg", "https://www.beholder.hu/pic/galeria/0420.jpg"};
        Random rand = new Random();
        List<CustomCardEntity> cards = new ArrayList<>();
        List<DeckEntity> decks = new ArrayList<>();
        User user = User.builder()
                .username("Test")
                .email("test@test.hu")
                .password("1234")
                .cards(cards)
                .decks(decks)
                .roles(new ArrayList<>())
                .build();

        MagicWallet newWallet = new MagicWallet();
        DeckEntity deck = new DeckEntity();
        DeckEntity deck2 = new DeckEntity();
        DeckEntity deck3 = new DeckEntity();
        user.addDeck(deck);
        deck.setUser(user);
        userService.createNewUser(user);
        deckRepository.save(deck);
        user.addDeck(deck2);
        deck.setUser(user);

        deckRepository.save(deck2);
        user.addDeck(deck3);
        deck.setUser(user);

        deckRepository.save(deck3);

        // Cascade type  and keep attention when to save

        for(int i = 0; i < generatedCardNumber; i++){

            CustomCardEntity testCard = CustomCardEntity.builder()
                    .name(randomNames[rand.nextInt((4 - 1) + 1) + 1] + i)
                    .imageUrl(randomPictures[rand.nextInt((4 - 1) + 1) + 1])
                    .price(100)
                    .build();

            user.addCard(testCard);
            testCard.setUser(user);
            deck.addCard(testCard);

        }

        deckRepository.save(deck);
    }
}
