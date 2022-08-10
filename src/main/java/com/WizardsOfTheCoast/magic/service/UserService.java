package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.*;
import com.WizardsOfTheCoast.magic.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import com.WizardsOfTheCoast.magic.entity.User;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {


    private UserRepository userRepository;
    private MagicWalletRepository magicWalletRepository;
    private CustomCardRepository customCardRepository;
    private DeckRepository deckRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       MagicWalletRepository magicWalletRepository,
                       CustomCardRepository customCardRepository,
                       DeckRepository deckRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.magicWalletRepository = magicWalletRepository;
        this.customCardRepository = customCardRepository;
        this.deckRepository = deckRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: {}", username);
        User user = userRepository.findByUsername(username);
        if(user == null){
            log.error("User not found in the Database!");
            throw new UsernameNotFoundException("User not found in the Database!");
        } else{
            log.info("User found in the database with name: {}",username);
        }
        Collection<SimpleGrantedAuthority> authorities =  new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public User findUserByNameOrEmail(String name, String email, String password){
        log.info("Fetching user by Name: {} and Email: {}", name, email);
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

    public boolean checkIfUserIsAlreadyExist(String name, String email){
        log.info("Checking if user is existing by Name: {} or Email: {}", name, email);
        List<User> userList = userRepository.findByUsernameOrEmail(name,email);
        return userList.size() > 0;
    }

    public User createNewUser(User user){
        log.info("Creating User: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = saveUser(user);
        addRoleToUser(savedUser.getUsername(),"ROLE_USER");
        userRepository.save(savedUser);
        return savedUser;
    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public Role saveRole(Role role){
        log.info("Role '{}' saved",role.getName());
        return roleRepository.save(role);
    }

    public User saveUser(User user){
        log.info("User '{}' saved",user.getUsername());
        return userRepository.save(user);
    }

    public void addRoleToUser(String username,String roleName){
        log.info("Adding Role '{}' to User '{}'",roleName,username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        log.info("Fetching all Users");
        List <User> users = userRepository.findAll();
        log.info("{} user fetched!",users.size());
        return users;
    }

//    public void createNewUser(User user){
//        User savedUser = userRepository.save(user);
//        createNewWalletForUser(savedUser);
//    }
//
//    private User createNewWalletForUser(User user){
//        MagicWallet newWallet = new MagicWallet();
//        user.setCurrency(newWallet);
//        userRepository.save(user);
//        return userRepository.save(user);
//    }

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

    public void saveUserForDeck(User user){
        userRepository.save(user);
    }

}
