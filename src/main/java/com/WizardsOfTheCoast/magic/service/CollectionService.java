package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.JPA.CollectionRepository;
import com.WizardsOfTheCoast.magic.entity.CollectionEntity;
import com.WizardsOfTheCoast.magic.entity.CustomCardEntity;
import com.WizardsOfTheCoast.magic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionService {
    private CollectionRepository collectionRepository;
    private UserService userService;

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

    public void saveCollection(CollectionEntity collection){
        collectionRepository.save(collection);
    }

    public CollectionEntity getCollectionById(Long collectionId){
        return collectionRepository.findById(collectionId).get();
    }

    public CollectionEntity getCollectionByName(String name){
        User user = userService.getUser(name);
        return collectionRepository.findById(user.getId()).get();
    }


}
