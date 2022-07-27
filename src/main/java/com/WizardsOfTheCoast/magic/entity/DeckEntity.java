package com.WizardsOfTheCoast.magic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Deck")
@Table(name ="deck")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false,
            nullable = false
    )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CustomCardEntity> deckCards = new ArrayList<>();


    public void addCard(CustomCardEntity card) {

        deckCards.add(card);
        card.setDeck(this);
    }

    public void addCard( CustomCardEntity card, boolean set) {
        if (card != null) {
            if(deckCards.contains(card)) {
                deckCards.set(deckCards.indexOf(card), card);
            }
            else {
                deckCards.add(card);
            }
            if (set) {
                card.setDeck(this, false);
            }
        }
    }

    public void removeCard(CustomCardEntity card) {
        deckCards.remove(card);
        card.setUser(null);
    }
    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof final DeckEntity deck))
            return false;

        if (id != null && deck.getId() != null) {
            return id.equals(deck.getId());
        }
        return false;
    }

    public void setUser(User user, boolean add) {
        this.user = user;
        if (user != null && add) {
            user.addDeck(this, false);
        }
    }
}
