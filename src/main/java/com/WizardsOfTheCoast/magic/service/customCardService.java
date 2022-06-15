package com.WizardsOfTheCoast.magic.service;

import com.WizardsOfTheCoast.magic.DAO.CustomCardDAO;
import com.WizardsOfTheCoast.magic.model.CardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class customCardService {

    private final CustomCardDAO customCardDao;


    @Autowired
    public customCardService(@Qualifier("customCardDao") CustomCardDAO customCardDao)
    {
        this.customCardDao = customCardDao;
    }

    public List<CardModel> getRooms(){
        return customCardDao.getAllCustomCard();
    }

    public void saveRoom(CardModel customCard){
        customCardDao.addCustomCard(customCard);
    }

}
