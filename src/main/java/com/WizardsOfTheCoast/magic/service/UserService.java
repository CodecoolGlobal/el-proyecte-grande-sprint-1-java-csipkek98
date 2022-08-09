package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CustomCardRepository;
import com.WizardsOfTheCoast.magic.JPA.DeckRepository;
import com.WizardsOfTheCoast.magic.JPA.MagicWalletRepository;
import com.WizardsOfTheCoast.magic.JPA.UserRepository;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import com.WizardsOfTheCoast.magic.entity.MagicWallet;
import com.WizardsOfTheCoast.magic.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;
    private MagicWalletRepository magicWalletRepository;
    private CustomCardRepository customCardRepository;
    private DeckRepository deckRepository;

    public UserService(UserRepository userRepository,
                       MagicWalletRepository magicWalletRepository,
                       CustomCardRepository customCardRepository,
                       DeckRepository deckRepository) {
        this.userRepository = userRepository;
        this.magicWalletRepository = magicWalletRepository;
        this.customCardRepository = customCardRepository;
        this.deckRepository = deckRepository;
    }

    public User findUserByNameOrEmail(String name, String email, String password){
        User user;
        if(email != null){
            user = userRepository.findByEmail(email);
        }else{
            user = userRepository.findByUsername(name);
        }
        if(password.equals(user.getPassword())){
            return user;
        }else{
            return null;
        }
    }

    public List<User> checkIfUserIsAlreadyExist(String name, String email){
        return userRepository.findByUsernameOrEmail(name,email);
    }

    public void createNewUser(User user){
        User savedUser = userRepository.save(user);
        createNewWalletForUser(savedUser);
    }

    private User createNewWalletForUser(User user){
        MagicWallet newWallet = new MagicWallet();
        user.setCurrency(newWallet);
        userRepository.save(user);
        return userRepository.save(user);
    }

    public void addCardToUserDeck(Long userId, Long deckId, CustomCardEntity card) {
        User user = userRepository.findById(userId).get();
        DeckEntity deck = deckRepository.findById(deckId).get();
        deck.addCard(card);
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public List<CustomCardEntity> getAllUserCards(Long id){
        User user = userRepository.findById(id).get();
        return user.getCards();
    }

    public List<DeckEntity> getAllUserDecks(Long id){
        User user = userRepository.findById(id).get();
        return user.getDecks();
    }

    public DeckEntity findDeckById(User user, Long id){
        return user.findDeckById(id);
    }

    public DeckEntity checkIfCardInAnyDeck(Long id, CustomCardEntity cardToDelete){
        User user = userRepository.findById(id).get();
        List<DeckEntity> decks = user.getDecks();
        DeckEntity destinationDeck = null;
        for (DeckEntity deck : decks) {
            for(CustomCardEntity card: deck.getCards()){
                if (card == cardToDelete) {
                    destinationDeck = deck;
                    break;
                }
            }
            }
        assert destinationDeck != null;
        return destinationDeck;
    }

        public void saveUser(User user){
            userRepository.save(user);
        }
}
