package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.MagicWalletRepository;
import com.WizardsOfTheCoast.magic.JPA.UserRepository;
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
        User savedUserWithWallet = createNewWalletForUser(savedUser);
    }

    private User createNewWalletForUser(User user){
        MagicWallet newWallet = new MagicWallet();
        newWallet.setUser(user);
        MagicWallet userWallet = magicWalletRepository.save(newWallet);
        user.setCurrency(userWallet);
        return userRepository.save(user);
    }

}
