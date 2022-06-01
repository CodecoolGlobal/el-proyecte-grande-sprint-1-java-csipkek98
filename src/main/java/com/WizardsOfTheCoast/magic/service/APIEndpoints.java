package com.WizardsOfTheCoast.magic.service;

public enum APIEndpoints {
    FILTER("https://api.scryfall.com/cards/search?q={rarity}={common}"),
    SEARCH("https://api.scryfall.com/cards/search?q={name}");

    private String path;
    APIEndpoints(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}