package com.WizardsOfTheCoast.magic.service;

public enum APIEndpoints {
    SEARCH("https://api.scryfall.com/cards/search?q={parameters}");

    private String path;
    APIEndpoints(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }

}
