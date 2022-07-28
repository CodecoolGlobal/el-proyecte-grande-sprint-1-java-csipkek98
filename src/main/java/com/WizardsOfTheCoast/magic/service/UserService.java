package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CollectionRepository;
import com.WizardsOfTheCoast.magic.JPA.MagicWalletRepository;
import com.WizardsOfTheCoast.magic.JPA.RoleRepository;
import com.WizardsOfTheCoast.magic.JPA.UserRepository;
import com.WizardsOfTheCoast.magic.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    MagicWalletRepository magicWalletRepository;
    @Autowired
    CollectionRepository collectionRepository;

    private final PasswordEncoder passwordEncoder;

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

    public List<User> checkIfUserIsAlreadyExist(String name, String email){
        log.info("Checking if user is existing by Name: {} or Email: {}", name, email);
        return userRepository.findByUsernameOrEmail(name,email);
    }

    public void createNewUser(User user){
        log.info("Creating User: {}", user.getUsername());
        User savedUser = saveUser(user);
//        User savedUserWithWallet = createNewWalletForUser(savedUser);
//        createNewCollectionForUser(savedUserWithWallet);
    }

//    private User createNewWalletForUser(User user){
//        log.info("Create new wallet for {}", user.getUsername());
//        MagicWallet newWallet = new MagicWallet();
//        newWallet.setUser(user);
//        MagicWallet userWallet = magicWalletRepository.save(newWallet);
//        user.setCurrency(userWallet);
//        return userRepository.save(user);
//    }
//
//    private void createNewCollectionForUser(User user){
//        log.info("Create new collect for {}", user.getUsername());
//        List<CustomCardEntity> empty = new ArrayList<>();
//        CollectionEntity collection = CollectionEntity.builder()
//                .cards(empty)
//                .build();
//        collection.setUser(user);
//        CollectionEntity collectionWithUser = collectionRepository.save(collection);
//        user.setCollectionEntity(collectionWithUser);
//        userRepository.save(user);
//    }

    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    public Role saveRole(Role role){
        log.info("Role '{}' saved",role.getName());
        return roleRepository.save(role);
    }

    public User saveUser(User user){
        log.info("User '{}' saved",user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void addRoleToUser(String username,String roleName){
        log.info("Adding Role '{}' to User '{}'",roleName,username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);;
    }

    public List<User> getAllUser(){
        log.info("Fetching all Users");
        List <User> users = userRepository.findAll();
        log.info("{} user fetched!",users.size());
        return users;
    }
}
