package com.WizardsOfTheCoast.magic.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name= "Collection")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "collection")
public class CollectionEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @SequenceGenerator(
            name = "collection_sequence",
            sequenceName = "custom_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collection_sequence"
    )
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "collection_id",
            referencedColumnName = "id"
    )
    private List<CustomCardEntity> cards = new ArrayList<>();


}
