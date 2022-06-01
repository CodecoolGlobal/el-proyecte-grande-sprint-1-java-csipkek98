package com.WizardsOfTheCoast.magic.model;

public class CardModel {

    private final String name;
    private final String id;
    private final String imageUrl;
    private float price;
    private final String description;
    private final String rarity;
    private final String type;

    public CardModel(CardBuilder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.imageUrl = builder.imageUrl;
        this.price = builder.price;
        this.description = builder.description;
        this.rarity = builder.rarity;
        this.type = builder.type;


    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getRarity() {
        return rarity;
    }

    public String getType() {
        return type;
    }

    public static class CardBuilder {

        private final String name;
        private final String id;
        private final String imageUrl;
        private float price;
        private String description;
        private String rarity;
        private String type;

        public CardBuilder(String name, String id, String imageUrl, String price) {
            this.name = name;
            this.id = id;
            this.imageUrl = imageUrl;
            this.price = Float.parseFloat(price);
        }

        public CardBuilder description(String description){
            this.description=description;
            return this;
        }

        public CardBuilder rarity(String rarity){
            this.rarity=rarity;
            return this;
        }

        public CardBuilder type(String type){
            this.type=type;
            return this;
        }

        public CardModel build() {
            CardModel card =  new CardModel(this);
            return card;
        }
    }
}
