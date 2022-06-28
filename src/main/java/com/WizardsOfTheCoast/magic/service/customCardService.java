package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CustomCardRepository;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class customCardService {

    private final CustomCardRepository customCardRepository;


    @Autowired
    public customCardService(@Qualifier("CustomCardRepository")  CustomCardRepository customCardRepository)
    {
        this.customCardRepository = customCardRepository;

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

}
