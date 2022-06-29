package com.WizardsOfTheCoast.magic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            sequenceName = "collection_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "collection_sequence"
    )
    private Long id;
    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<CustomCardEntity> cards = new ArrayList<>();
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private User user;


}
