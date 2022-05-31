package com.WizardsOfTheCoast.magic.service;

public enum CardEndpoints {
    CARD_PICTURES("https://api.magicthegathering.io/v1/cards?pageSize={number}");

    private String path;
    CardEndpoints(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
