package com.WizardsOfTheCoast.magic.Data_sample;

import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.model.CardModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomCardCreator {

    public static List<CustomCardEntity> initialize() {
        List<CustomCardEntity> cards = new ArrayList<>();
        CustomCardEntity custom1 = CustomCardEntity.builder()
                .name("Mr.T the Humble")
                .imageUrl("https://i.im.ge/2022/06/16/rJGMJY.jpg")
                .price(1000)
                .build();
        CustomCardEntity custom2 = CustomCardEntity.builder()
                .name("Bob the Almighty")
                .imageUrl("https://i.im.ge/2022/06/16/rJGdnr.jpg")
                .price(2000)
                .build();

        cards.add(custom1);
        cards.add(custom2);
        return cards;
    }


}
