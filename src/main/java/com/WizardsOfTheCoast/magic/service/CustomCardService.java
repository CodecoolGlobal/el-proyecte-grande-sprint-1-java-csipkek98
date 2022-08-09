package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CustomCardRepository;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomCardService {

    private final CustomCardRepository customCardRepository;

    public CustomCardEntity saveCard(CustomCardEntity card){
        return customCardRepository.save(card);
    }

    public CustomCardEntity findCardByName(String name){
        return customCardRepository.findCardByName(name);
    }

    public List<CustomCardEntity> getAllCustomCard(){
        return customCardRepository.findAll();
    }


    public void deleteCustomCardById(long customId){
        CustomCardEntity customCardEntity = customCardRepository.findById(customId).get();
        customCardRepository.delete(customCardEntity);
    }

    public CustomCardEntity findCardById(Long id){
        return customCardRepository.findById(id).get();
    }

}
