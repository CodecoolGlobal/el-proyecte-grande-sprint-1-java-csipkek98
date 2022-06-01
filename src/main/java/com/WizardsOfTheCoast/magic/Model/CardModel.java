package com.WizardsOfTheCoast.magic.Model;

public class CardModel {

    private final String name;
    private final int id;
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

    public int getId() {
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
        private final int id;
        private final String imageUrl;
        private float price;
        private String description;
        private String rarity;
        private String type;

        public CardBuilder(String name, int id, String imageUrl, float price) {
            this.name = name;
            this.id = id;
            this.imageUrl = imageUrl;
            this.price = price;
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
