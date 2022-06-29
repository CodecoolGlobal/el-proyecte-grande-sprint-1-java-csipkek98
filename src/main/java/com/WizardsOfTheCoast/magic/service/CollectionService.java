package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CollectionRepository;
import com.WizardsOfTheCoast.magic.entity.CollectionEntity;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    private CollectionRepository collectionRepository;

    @Autowired
    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public void addCustomCardToCollection(Long cardCollection, CustomCardEntity cardToAdd){
        CollectionEntity collection = collectionRepository.findById(cardCollection).get();
        collection.getCards().add(cardToAdd);
        collectionRepository.save(collection);
    }

    public List<CollectionEntity> getAllCollection(){
        return collectionRepository.findAll();
    }
}
