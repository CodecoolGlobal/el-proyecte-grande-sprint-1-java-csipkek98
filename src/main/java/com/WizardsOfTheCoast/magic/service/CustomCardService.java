package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CustomCardRepository;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomCardService {

    private final CustomCardRepository customCardRepository;


    @Autowired
    public CustomCardService(@Qualifier("CustomCardRepository")  CustomCardRepository customCardRepository)
    {
        this.customCardRepository = customCardRepository;

    }

    public void saveCard(CustomCardEntity card){
        customCardRepository.save(card);
    }

    public CustomCardEntity findCardByName(String name){
        return customCardRepository.findCardByName(name);
    }

    public List<CustomCardEntity> getAllCustomCard(){
        return customCardRepository.findAll();
    }

    public void addCard(CustomCardEntity customCardEntity){
        customCardRepository.save(customCardEntity);
    }

    public void deleteCustomCardById(long customId){
        CustomCardEntity customCardEntity = customCardRepository.findById(customId).get();
        customCardRepository.delete(customCardEntity);
    }

    public CustomCardEntity findCustomCardFromCollectionByName(List<CustomCardEntity> cards, String name){

        for (CustomCardEntity card : cards) {

            if(card.getName().equals(name)){
                System.out.println(card.getName());
                return card;
            }
        }
        return null;
    }

    public CustomCardEntity findCardById(Long id){
        return customCardRepository.findById(id).get();
    }

}
