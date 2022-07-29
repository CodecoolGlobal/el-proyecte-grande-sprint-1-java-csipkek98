package com.WizardsOfTheCoast.magic;

import java.util.ArrayList;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.WizardsOfTheCoast.magic.service.UserService;
import com.WizardsOfTheCoast.magic.service.CustomCardService;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.JPA.UserRepository;

@DataJpaTest
@ComponentScan(basePackages = "com.WizardsOfTheCoast.magic")
public class CustomCardDbIntegrationTest {
    private UserService userService;
    private CustomCardService customCardService;
    private UserRepository userRepository;

    @Autowired
    public CustomCardDbIntegrationTest(UserService userService,
                                       CustomCardService customCardService,
                                       UserRepository userRepository) {
        this.userService = userService;
        this.customCardService = customCardService;
        this.userRepository = userRepository;
    }

    @Test
    public void createAndDelete() {
        var cc = CustomCardEntity
            .builder()
            .name("Opt")
            .imageUrl("/temp.jpg")
            .price(123)
            .build();


        var user = User
            .builder()
            .username("Benec")
            .email("benec@codecool.com")
            .password("213345")
            .cards(new ArrayList<>())
            .build();

        customCardService.addCard(cc);
        userService.createNewUser(user);

        user.addCard(cc);
        var saved_user = userRepository.save(user);
        assertEquals(saved_user.getCards().size(), 1);
    }
}
