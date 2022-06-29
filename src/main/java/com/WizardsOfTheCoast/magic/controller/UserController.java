package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000","https://lemon-stone-05afd8203.1.azurestaticapps.net"},
        methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
        , allowedHeaders = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/usercheck")
    public List<User> getUsersWithName(@RequestBody Map<String, String> payload){
        System.out.println(payload);
            return userService.findUsersByNameOrEmail(payload.get("username"), payload.get("email"));
    }

    @PostMapping("/register")
    public void saveNewUserToDB(@RequestBody User newUser){
        userService.createNewUser(newUser);
    }

}