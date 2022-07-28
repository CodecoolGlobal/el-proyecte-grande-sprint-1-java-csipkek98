package com.WizardsOfTheCoast.magic.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="User")
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String username;
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    @JsonIgnore
    private MagicWallet currency;
    @OneToMany(
            cascade = CascadeType.ALL, mappedBy = "user")
    private List<CustomCardEntity> cards = new ArrayList<>();
    @OneToMany(
            cascade = CascadeType.ALL, mappedBy = "user")
    private List<DeckEntity> decks = new ArrayList<>();


    public void addCard(CustomCardEntity card) {
        cards.add(card);
        card.setUser(this);
    }

    public void addCard( CustomCardEntity card, boolean set) {
        if (card != null) {
            if(cards.contains(card)) {
                cards.set(cards.indexOf(card), card);
            }
            else {
                cards.add(card);
            }
            if (set) {
                card.setUser(this, false);
            }
        }
    }

    public void removeCard(CustomCardEntity card) {
        cards.remove(card);
        card.setUser(null);
    }

    public void removeDeck(DeckEntity deck) {
        decks.remove(deck);
        deck.setUser(null);
    }

    public void addDeck(DeckEntity deck) {
        decks.add(deck);
        deck.setUser(this);
    }

    public void addDeck( DeckEntity deck, boolean set) {
        if (deck != null) {
            if(decks.contains(deck)) {
                decks.set(decks.indexOf(deck), deck);
            }
            else {
                decks.add(deck);
            }
            if (set) {
                deck.setUser(this, false);
            }
        }
    }


    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof final User user))
            return false;

        if (id != null && user.getId() != null) {
            return id.equals(user.getId());
        }
        return false;
    }

    public void setCurrency(MagicWallet currency) {
        if ( currency == null) {
            if (  this.currency != null ) {
                this.currency.setUser(null);
            }
        }
        else {
            currency.setUser(this);
        }
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", currency=" + currency +
                ", cards=" + cards +
                ", decks=" + decks +
                '}';
    }


    public CustomCardEntity findCustomCardByName(String name) {
        for (CustomCardEntity card : cards) {
            if(card.getName().equals(name)){
                return card;
            }
        }
        return null;
    }

    public DeckEntity findDeckById(Long id){
        for (DeckEntity deck : decks) {
            if(Objects.equals(deck.getId(), id)){
                return deck;
            }
        }
        return null;
    }
}
