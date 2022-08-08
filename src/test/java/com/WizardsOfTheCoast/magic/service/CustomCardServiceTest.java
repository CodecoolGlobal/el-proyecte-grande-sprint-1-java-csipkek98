package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CustomCardRepository;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CustomCardServiceTest {


    @Mock
    private CustomCardRepository customCardRepository;
    @Mock
    private User user;
    @InjectMocks
    private CustomCardService service;


    @Test
    public void saveCard() {
        CustomCardEntity entity = CustomCardEntity.builder()
                .id(1L)
                .name("test")
                .imageUrl("testpic")
                .price(100)
                .user(user)
                .build();
        when(customCardRepository.save(Mockito.any(CustomCardEntity.class))).thenReturn(entity);
        service.saveCard(entity);
        Assertions.assertNotNull(entity);
        Assertions.assertEquals("test", entity.getName());
    }

    @Test
    void findCardByName() {
        CustomCardEntity entity = CustomCardEntity.builder()
                .id(1L)
                .name("testTwo")
                .imageUrl("testpic")
                .price(100)
                .user(user)
                .build();
        when(customCardRepository.findCardByName(Mockito.anyString())).thenReturn(entity);
        CustomCardEntity customCard = service.findCardByName("testTwo");
        Assertions.assertNotNull(customCard);
        Assertions.assertEquals("testTwo", customCard.getName());
    }

    @Test
    void getAllCustomCard() {
        List<CustomCardEntity> cards = new ArrayList<>();

        CustomCardEntity entity = CustomCardEntity.builder()
                .id(1L)
                .name("testOne")
                .imageUrl("testpic")
                .price(100)
                .user(user)
                .build();
        CustomCardEntity entityTwo = CustomCardEntity.builder()
                .id(2L)
                .name("testTwo")
                .imageUrl("testpic")
                .price(100)
                .user(user)
                .build();
        cards.add(entity);
        cards.add(entityTwo);
        when(customCardRepository.findAll()).thenReturn(cards);
        List<CustomCardEntity> resultList = service.getAllCustomCard();
        assertThat(resultList).isNotNull().isNotEmpty().hasSize(2);
    }

    @Test
    void deleteCustomCardById() {
        //TODO
    }

    @Test
    void findCardById() {
        CustomCardEntity entity = CustomCardEntity.builder()
                .id(1L)
                .name("testTwo")
                .imageUrl("testpic")
                .price(100)
                .user(user)
                .build();
        Optional<CustomCardEntity> optionalCard = Optional.of(entity);
        when(customCardRepository.findById(Mockito.anyLong())).thenReturn(optionalCard);
        CustomCardEntity customCard = service.findCardById(1L);
        Assertions.assertNotNull(customCard);
        Assertions.assertEquals("testTwo", customCard.getName());
    }
}