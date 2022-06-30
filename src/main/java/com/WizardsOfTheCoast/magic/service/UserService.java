package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CollectionRepository;
import com.WizardsOfTheCoast.magic.JPA.MagicWalletRepository;
import com.WizardsOfTheCoast.magic.JPA.UserRepository;
import com.WizardsOfTheCoast.magic.entity.CollectionEntity;
import com.WizardsOfTheCoast.magic.entity.MagicWallet;
import com.WizardsOfTheCoast.magic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MagicWalletRepository magicWalletRepository;
    @Autowired
    CollectionRepository collectionRepository;

    public List<User> findUsersByName(String name){
        return userRepository.findByUsername(name);
    }

    public List<User> checkIfUserIsAlreadyExist(String name, String email){
        return userRepository.findByUsernameOrEmail(name,email);
    }

    public void createNewUser(User user){
        User savedUser = userRepository.save(user);
        User savedUserWithWallet = createNewWalletForUser(savedUser);
        createNewCollectionForUser(savedUserWithWallet);
    }

    private User createNewWalletForUser(User user){
        MagicWallet newWallet = new MagicWallet();
        newWallet.setUser(user);
        MagicWallet userWallet = magicWalletRepository.save(newWallet);
        user.setCurrency(userWallet);
        return userRepository.save(user);
    }

    private void createNewCollectionForUser(User user){
        CollectionEntity collection = new CollectionEntity();
        collection.setUser(user);
        CollectionEntity collectionWithUser = collectionRepository.save(collection);
        user.setCollectionEntity(collectionWithUser);
        userRepository.save(user);
    }
}
