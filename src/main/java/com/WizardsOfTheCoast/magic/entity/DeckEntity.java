package com.WizardsOfTheCoast.magic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Deck")
@Table(name ="deck")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Builder
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
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
    private List<CustomCardEntity> cards = new ArrayList<>();

    public void addCard(CustomCardEntity card) {
        cards.add(card);
    }

    public void removeCard(CustomCardEntity card) {
        cards.remove(card);
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

    public void deleteCard(Long cardId){
        cards.removeIf(card -> card.getId().equals(cardId));
    }
}
