package com.WizardsOfTheCoast.magic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="CustomCard")
@Table(name = "customcard")
public class CustomCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name ="id",
            updatable = false,
            nullable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private  String name;
    @Column(
            name = "imageUrl",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private  String imageUrl;
    @Column(
            name = "price",
            nullable = false,
            columnDefinition = "integer"
    )
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public boolean equals(Object object) {
        if (object == this)
            return true;
        if (!(object instanceof final CustomCardEntity card))
            return false;

        if (id != null && card.getId() != null) {
            return id.equals(card.getId());
        }
        return false;

    }


    public void setUser(User user, boolean add) {
        this.user = user;
        if (user != null && add) {
            user.addCard(this, false);
        }
    }


    @Override
    public String toString() {
        return "CustomCardEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                '}';
    }
}
