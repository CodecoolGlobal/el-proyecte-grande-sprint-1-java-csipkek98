package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.service.CustomCardService;
import com.WizardsOfTheCoast.magic.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomCardController.class)
class CustomCardControllerTest {

    @MockBean
    private CustomCardService cardService;
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetClients() {
    }

    @Test
    void testAddCustomCard() throws Exception {
        CustomCardEntity card = CustomCardEntity.builder()
                .id(1L)
                .name("TestOne")
                .imageUrl("pic1")
                .price(100)
                .build();
        User user = User.builder()
                .id(1L)
                .cards(new ArrayList<>())
                .build();
        user.addCard(card);
        card.setUser(user);
        when(userService.findUserById(1L)).thenReturn(user);
        when(cardService.saveCard(card)).thenReturn(card);
        mockMvc.perform(post("/custom")
                .contentType(APPLICATION_JSON)
                .content("{\"sessionId\": \"1\" " +
                                ",\"name\": \"TestOne\",  " +
                                "\"pic\": \"pic1\", " +
                                "\"price\": \"100\"}"
                        )
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION,
                        "http://localhost/custom/search/1/TestOne"));
    }

    @Test
    void testDeleteCustomCard() {
    }

    @Test
    void testFindCustomCardByName() {
    }
}