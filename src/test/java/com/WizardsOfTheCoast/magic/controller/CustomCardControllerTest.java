package com.WizardsOfTheCoast.magic.controller;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.DeckEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import com.WizardsOfTheCoast.magic.service.CustomCardService;
import com.WizardsOfTheCoast.magic.service.DeckService;
import com.WizardsOfTheCoast.magic.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomCardController.class)
class CustomCardControllerTest {

    @MockBean
    private CustomCardService cardService;
    @MockBean
    private UserService userService;
    @MockBean
    private DeckService deckService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void testGetCustomCards() {

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
    void testDeleteCustomCard() throws Exception {
        CustomCardEntity card = CustomCardEntity.builder()
                .id(1L)
                .name("TestOne")
                .imageUrl("pic1")
                .price(100)
                .build();
        DeckEntity deck = DeckEntity.builder()
                .id(1L)
                .cards(new ArrayList<>())
                .build();
        when(cardService.findCardById(1L)).thenReturn(card);
        when(userService.checkIfCardInAnyDeck(1L,card)).thenReturn(deck);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/custom/user/1/card_id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindCustomCardByName() throws Exception {
        User user = User.builder()
                .id(1L)
                .cards(new ArrayList<>())
                .build();
        CustomCardEntity cardToFind = CustomCardEntity.builder()
                .id(1L)
                .name("TestCard")
                .imageUrl("pic1")
                .price(100)
                .build();
        when(userService.findUserById(1L)).thenReturn(user);
        when(userService.findCustomCardByNameFromUser("TestCard", user)).thenReturn(cardToFind);
        mockMvc.perform(get("/custom/search/{id}/{name}", 1L, "TestCard"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.name").value("TestCard"));
    }
}