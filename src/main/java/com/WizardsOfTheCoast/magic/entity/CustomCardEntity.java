package com.WizardsOfTheCoast.magic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name ="CustomCard")
@Table(name = "customcard")
public class CustomCardEntity {
    @Id
    @SequenceGenerator(
            name = "custom_card_sequence",
            sequenceName = "custom_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "custom_card_sequence"
    )
    @Column(
            name ="id",
            updatable = false,
            nullable = false
    )
    private long id;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "collection_id", nullable = false)
    @JsonIgnore
    private CollectionEntity collection;

}
