package com.WizardsOfTheCoast.magic.DAO;

import com.WizardsOfTheCoast.magic.model.CardModel;

import java.util.List;

public interface CustomCardDAO {

    void addCustomCard();
    List<CardModel> getAllCustomCard();
}
